package be.hikage.spring.ws.model;

/**
 * Created by IntelliJ IDEA.
 * User: Hikage
 * Date: 16 nov. 2007
 * Time: 21:02:28
 * To change this template use File | Settings | File Templates.
 */
public class Traduction {

    private String auteur;
    private String texte;

    public Traduction(String auteur, String texte) {
        this.auteur = auteur;
        this.texte = texte;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
