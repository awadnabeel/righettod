package be.hikage.xml.jdom;

import be.hikage.xml.model.Contact;
import be.hikage.xml.model.Email;
import org.jdom.Document;
import org.jdom.Element;

import java.util.List;


public class Writer {

    public Document createDocument(List<Contact> contacts) {

        Element root = new Element("contacts");
        Document document = new Document(root);

        for (Contact contact : contacts) {
            addContact(root, contact);
        }

        return document;
    }

    private void addContact(Element root, Contact contact) {

        Element contactElement = new Element("contact");

        Element nomElement = new Element("nom");
        nomElement.setText(contact.getNom());

        contactElement.addContent(nomElement);

        Element prenomElement = new Element("prenom");
        prenomElement.setText(contact.getPrenom());

        contactElement.addContent(prenomElement);


        for (Email email : contact.getEmails()) {
            Element emailElement = new Element("email");
            emailElement.setAttribute("type", email.getType() == Email.EMAIL_TYPE.PERSONNELLE ? "personnelle" : "professionelle");
            emailElement.setText(email.getEmail());
            contactElement.addContent(emailElement);

        }

        Element descriptionElement = new Element("description");
        descriptionElement.setText(contact.getDescription());

        contactElement.addContent(descriptionElement);

        root.addContent(contactElement);
    }
}
