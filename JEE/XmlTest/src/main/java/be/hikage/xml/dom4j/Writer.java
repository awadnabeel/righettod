package be.hikage.xml.dom4j;

import be.hikage.xml.model.Contact;
import be.hikage.xml.model.Email;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;


public class Writer {

    public Document createDocument(List<Contact> contacts) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("contacts");


        for (Contact contact : contacts) {

            createContact(contact, root.addElement("contact"));
        }

        document.setRootElement(root);
        return document;
    }

    private void createContact(Contact contact, Element root) {


        root.addElement("prenom").setText(contact.getPrenom());
        root.addElement("nom").setText(contact.getNom());
        for (Email email : contact.getEmails()) {
            root.addElement("email").addAttribute("type", email.getType() == Email.EMAIL_TYPE.PERSONNELLE ? "personnelle" : "professionnelle").setText(email.getEmail());
        }
        root.addElement("description").addCDATA(contact.getDescription());
    }


}
