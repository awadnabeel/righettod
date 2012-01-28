package be.hikage.xml.jdom;

import be.hikage.xml.model.Contact;
import be.hikage.xml.model.Email;
import junit.framework.TestCase;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;

import java.util.ArrayList;
import java.util.List;



public class JdomTest extends TestCase {

    public void testParser() {

        Parseur parseur = new Parseur();

        List<Contact> contacts = parseur.parser(getClass().getClassLoader().getResourceAsStream("input.xml"));

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

    public void testWriter() throws JDOMException {

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

        XPath xpathNom = XPath.newInstance("//contacts/contact/nom");
        XPath xpathPrenom = XPath.newInstance("//contacts/contact/prenom");
        XPath xpathEmail = XPath.newInstance("//contacts/contact/email");


        Element nomElement = (Element) xpathNom.selectSingleNode(document);
        Element prenomElement = (Element) xpathPrenom.selectSingleNode(document);
        Element emailElement = (Element) xpathEmail.selectSingleNode(document);

        assertEquals("nom", nomElement.getText());
        assertEquals("prenom", prenomElement.getText());
        assertEquals("hikage@hikage.be", emailElement.getText());
        assertEquals("personnelle", emailElement.getAttributeValue("type"));

    }
}
