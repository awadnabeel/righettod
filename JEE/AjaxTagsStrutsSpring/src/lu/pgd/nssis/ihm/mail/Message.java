package lu.pgd.nssis.ihm.mail;

import javax.mail.Address;
import javax.mail.MessagingException;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * Classe (DTO) pour stocker les informations provenant d'un message de type
 * mail
 * 
 * @author Dominique RIGHETTO (Unilog, a LogicaCMG company) 21 oct. 06<br>
 */
public class Message implements Serializable{

	/** Liste des destinataires du message séparé par un espace */
	String recipients = null;

	/** Liste des expéditeurs séparé par un espace */
	String from = null;

	/** Contenu du message */
	String content = null;

	/** Type du contenu du message */
	String contentType = null;

	/** Sujet du message */
	String subject = null;

	/** Date de reception du message */
	Date receivedDate = null;

	/** Date d'envoi du message */
	Date sentDate = null;

	/** Position du message dans le répertoire d'hébergement */
	int messageNumber = -1;

	/** Taille du message */
	int size = -1;

	/**
	 * Constructeur par défaut
	 * 
	 * @author Dominique RIGHETTO (Unilog, a LogicaCMG company)
	 * @param msg
	 *            Objet message source
	 * @throws javax.mail.MessagingException
	 * @throws java.io.IOException
	 */
	public Message(javax.mail.Message msg) throws MessagingException,
			IOException {

		Address[] adrs = null;
		Object tmpObj = null;

		// On peuple les attributs à partir de l'objet original
		adrs = msg.getAllRecipients();
		if ((adrs != null) && (adrs.length > 0)) {
			for (Address adr : adrs) {
				recipients = adr.toString().concat(" ");
			}
			recipients = recipients.trim();
		}

		adrs = msg.getFrom();
		if ((adrs != null) && (adrs.length > 0)) {
			for (Address adr : adrs) {
				from = adr.toString().concat(" ");
			}
			from = from.trim();
		}

		tmpObj = msg.getContent();
		if (tmpObj != null)
			content = tmpObj.toString();

		contentType = msg.getContentType();
		subject = msg.getSubject();
		receivedDate = msg.getReceivedDate();
		sentDate = msg.getSentDate();
		messageNumber = msg.getMessageNumber();
		size = msg.getSize();
	}

	/**
	 * Getter pour content
	 * 
	 * @author Dominique RIGHETTO (Unilog, a LogicaCMG company)
	 * @return Le contenu du message
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Getter pour contentType
	 * 
	 * @author Dominique RIGHETTO (Unilog, a LogicaCMG company)
	 * @return Le type de contenu
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * Getter pour from
	 * 
	 * @author Dominique RIGHETTO (Unilog, a LogicaCMG company)
	 * @return La liste des expéditeurs séparé par un espace
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Getter pour messageNumber
	 * 
	 * @author Dominique RIGHETTO (Unilog, a LogicaCMG company)
	 * @return La position du message dans le répertoire d'hébergement
	 */
	public int getMessageNumber() {
		return messageNumber;
	}

	/**
	 * Getter pour receivedDate
	 * 
	 * @author Dominique RIGHETTO (Unilog, a LogicaCMG company)
	 * @return La date de reception
	 */
	public Date getReceivedDate() {
		return receivedDate;
	}

	/**
	 * Getter pour recipients
	 * 
	 * @author Dominique RIGHETTO (Unilog, a LogicaCMG company)
	 * @return La liste des destinataires séparé par un espace
	 */
	public String getRecipients() {
		return recipients;
	}

	/**
	 * Getter pour sentDate
	 * 
	 * @author Dominique RIGHETTO (Unilog, a LogicaCMG company)
	 * @return La date d'envoie
	 */
	public Date getSentDate() {
		return sentDate;
	}

	/**
	 * Getter pour size
	 * 
	 * @author Dominique RIGHETTO (Unilog, a LogicaCMG company)
	 * @return La taille du message
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Getter pour subject
	 * 
	 * @author Dominique RIGHETTO (Unilog, a LogicaCMG company)
	 * @return Le sujet du message
	 */
	public String getSubject() {
		return subject;
	}

}
