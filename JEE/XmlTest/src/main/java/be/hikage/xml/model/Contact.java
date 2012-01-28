package be.hikage.xml.model;

import java.util.ArrayList;
import java.util.List;


public class Contact {
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Contact");
        sb.append("{description='").append(description).append('\'');
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", emails=").append(emails);
        sb.append('}');
        return sb.toString();
    }

    private String nom;
    private String prenom;
    private List<Email> emails = new ArrayList<Email>();
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }
}
