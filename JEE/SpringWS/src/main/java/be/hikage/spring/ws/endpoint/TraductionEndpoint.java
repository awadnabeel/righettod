package be.hikage.spring.ws.endpoint;

import be.hikage.spring.ws.model.Traduction;
import be.hikage.spring.ws.service.TraductionService;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.xpath.XPath;
import org.springframework.ws.server.endpoint.AbstractJDomPayloadEndpoint;

/**
 * Created by IntelliJ IDEA.
 * User: Hikage
 * Date: 9 nov. 2007
 * Time: 22:14:20
 * To change this template use File | Settings | File Templates.
 */
public class TraductionEndpoint extends AbstractJDomPayloadEndpoint {

    private TraductionService traductionService;

    public void setTraductionService(TraductionService traductionService) {
        this.traductionService = traductionService;
    }

    protected Element invokeInternal(Element request) throws Exception {

        Namespace namespace = Namespace.getNamespace("traduction", "http://www.hikage.be/schema/traduction");


        // Création des requête XPath pour récupérer les informations
        XPath langueOrigineExpression = XPath.newInstance("//traduction:TraductionRequest/traduction:langueOrigine");
        langueOrigineExpression.addNamespace(namespace);
        XPath langueDestinationExpression = XPath.newInstance("//traduction:TraductionRequest/traduction:langueDestination");
        langueDestinationExpression.addNamespace(namespace);
        XPath texteExpression = XPath.newInstance("//traduction:TraductionRequest/traduction:texte");
        texteExpression.addNamespace(namespace);

        // Récupération des informations à partir de la requête
        String langueOrigine = langueOrigineExpression.valueOf(request);
        String langueDestination = langueDestinationExpression.valueOf(request);
        String texteOriginal = texteExpression.valueOf(request);

        // Appel au service pour la traduction
        Traduction traduction = traductionService.traduitTexte(langueOrigine, langueDestination, texteOriginal);


        // Création de la réponse
        Element root = new Element("TraductionResponse", namespace);
        Element auteur = new Element("auteur", namespace);
        auteur.setText(traduction.getAuteur());

        Element texteTraduit = new Element("texte", namespace);
        texteTraduit.setText(traduction.getTexte());

        root.addContent(auteur);
        root.addContent(texteTraduit);

        return root;
    }
}
