package be.hikage.spring.ws.service;

import be.hikage.spring.ws.model.Traduction;

/**
 * Created by IntelliJ IDEA.
 * User: Hikage
 * Date: 17 nov. 2007
 * Time: 23:30:09
 * To change this template use File | Settings | File Templates.
 */
public class TraductionServiceImpl implements TraductionService {
    public Traduction traduitTexte(String langueOrigine, String langueDestination, String texte) {
        return new Traduction("Gildas Cuisinier", "Le texte traduit : " + texte);
    }
}
