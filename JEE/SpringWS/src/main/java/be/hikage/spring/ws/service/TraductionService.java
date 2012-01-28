package be.hikage.spring.ws.service;

import be.hikage.spring.ws.model.Traduction;

/**
 * Created by IntelliJ IDEA.
 * User: Hikage
 * Date: 16 nov. 2007
 * Time: 21:01:42
 * To change this template use File | Settings | File Templates.
 */
public interface TraductionService {

    public Traduction traduitTexte(String langueOrigine, String langueDestination, String texte);
}
