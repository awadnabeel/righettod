package be.hikage.xml.dom4j;

import be.hikage.xml.model.Contact;
import be.hikage.xml.model.Email;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Parseur {


    public List<Contact> parser(InputStream input) {

        SAXReader reader = new SAXReader();

        try {
            Document document = reader.read(input);
            List<Contact> result = new ArrayList<Contact>();

            for (Iterator<Element> it = document.getRootElement().elementIterator("contact"); it.hasNext();) {
                Element contactElement = it.next();

                Node nomElement = contactElement.selectSingleNode("nom");
                Node prenomElement = contactElement.selectSingleNode("prenom");
                List<Node> emailsElement = contactElement.selectNodes("email");
                Node descriptionElement = contactElement.selectSingleNode("description");

                Contact contact = new Contact();
                contact.setNom(nomElement.getText());
                contact.setPrenom(prenomElement.getText());
                if(descriptionElement != null)
                contact.setDescription(descriptionElement.getText());


                for (Node emailElement : emailsElement) {
                    String typeString = emailElement.valueOf("@type");
                    Email.EMAIL_TYPE type;
                    if ("personnelle".equals(typeString))
                        type = Email.EMAIL_TYPE.PERSONNELLE;
                    else
                        type = Email.EMAIL_TYPE.PROFESSIONELLE;

                    String email = emailElement.getText();
                    Email EmailType = new Email(type, email);

                    contact.getEmails().add(EmailType);
                }

                result.add(contact);
            }
            return result;
        } catch (DocumentException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
