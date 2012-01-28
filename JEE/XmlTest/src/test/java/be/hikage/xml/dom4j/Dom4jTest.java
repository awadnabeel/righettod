package be.hikage.xml.dom4j;

import be.hikage.xml.model.Contact;
import be.hikage.xml.model.Email;
import junit.framework.TestCase;
import org.dom4j.Document;

import java.util.ArrayList;
import java.util.List;


public class Dom4jTest extends TestCase {


    public void testParseur() {

        Parseur parseur = new Parseur();
        List<Contact> contacts = parseur.parser(getClass().getClassLoader().getResourceAsStream("input.xml"));

        assertNotNull(contacts);

        Contact contact = contacts.get(0);
        assertEquals("Cuisinier", contact.getNom());
        assertEquals("Gildas", contact.getPrenom());
        assertEquals("hikage@hikage.be", contact.getEmails().get(0).getEmail());
        assertEquals(Email.EMAIL_TYPE.PERSONNELLE, contact.getEmails().get(0).getType());

        contact = contacts.get(1);
        assertEquals("Righetto", contact.getNom());
        assertEquals("Dominique", contact.getPrenom());
        assertEquals("dominique.righetto@logica.com", contact.getEmails().get(0).getEmail());
        assertEquals(Email.EMAIL_TYPE.PROFESSIONELLE, contact.getEmails().get(0).getType());

    }

    public void testWriter() {
        Writer writer = new Writer();

        List<Contact> contacts = new ArrayList<Contact>();
        Contact contact = new Contact();
        contact.setNom("nom");
        contact.setPrenom("prenom");
        contact.setDescription("<description />");
        Email email = new Email(Email.EMAIL_TYPE.PERSONNELLE, "hikage@hikage.be");
        contact.getEmails().add(email);

        contacts.add(contact);

        Document document = writer.createDocument(contacts);


        assertEquals("nom", document.selectSingleNode("//contacts/contact/nom").getText());
        assertEquals("prenom", document.selectSingleNode("//contacts/contact/prenom").getText());
        assertEquals("hikage@hikage.be", document.selectSingleNode("//contacts/contact/email").getText());
        assertEquals("personnelle", document.selectSingleNode("//contacts/contact/email").valueOf("@type"));
    }
}
