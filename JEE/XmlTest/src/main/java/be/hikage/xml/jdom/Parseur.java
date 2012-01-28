package be.hikage.xml.jdom;

import be.hikage.xml.model.Contact;
import be.hikage.xml.model.Email;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Parseur {

    public List<Contact> parser(InputStream input) {

        SAXBuilder sxb = new SAXBuilder();


        try {
            Document document = sxb.build(input);

            XPath xpath = XPath.newInstance("/contacts/contact");

            List contacts = xpath.selectNodes(document);

            return parseContact(contacts);


        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();  
        }

        return null;
    }

    private List<Contact> parseContact(List contacts) throws JDOMException {

        List<Contact> result = new ArrayList<Contact>();

        XPath queryNom = XPath.newInstance("nom");
        XPath queryPrenom = XPath.newInstance("prenom");
        XPath queryEmail = XPath.newInstance("email");


        for (Object contact : contacts) {
            Contact newContact = new Contact();
            Object nom = queryNom.selectSingleNode(contact);
            Object prenom = queryPrenom.selectSingleNode(contact);
            List emails = queryEmail.selectNodes(contact);
            newContact.setNom(((Element) nom).getText());
            newContact.setPrenom(((Element) prenom).getText());
            result.add(newContact);

            for (Object mail : emails) {

                Element elem = (Element) mail;
                String type = elem.getAttributeValue("type");
                Email.EMAIL_TYPE typeEmail = "personnelle".equals(type) ? Email.EMAIL_TYPE.PERSONNELLE : Email.EMAIL_TYPE.PROFESSIONELLE;
                String emailAddresse = elem.getText();


                Email email = new Email(typeEmail, emailAddresse);
                newContact.getEmails().add(email);
            }

        }

        return result;
    }
}
