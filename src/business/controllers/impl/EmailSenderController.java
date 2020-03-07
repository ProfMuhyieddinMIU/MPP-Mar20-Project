package business.controllers.impl;

import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import business.ConfigurationFactory;
import business.controllers.interfaces.EmailSenderControllerInterface;
import business.customExceptions.LibrarySystemException;

public class EmailSenderController implements EmailSenderControllerInterface {

	public void sendEmail(String email, String bookTitle, String isbn, String memberName)
			throws LibrarySystemException {

		String fromEmail;
		String password;
		String subject;
		String body;
		try {
			fromEmail = new String(Base64.getDecoder().decode(ConfigurationFactory.getProperty("email.name")));
			password = new String(Base64.getDecoder().decode(ConfigurationFactory.getProperty("email.password")));
			subject = ConfigurationFactory.getProperty("email.subject");
			body = String.format(ConfigurationFactory.getProperty("email.body"), memberName, bookTitle, isbn);

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
			props.put("mail.smtp.port", "587"); // TLS Port
			props.put("mail.smtp.auth", "true"); // enable authentication
			props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

			// create Authenticator object to pass in Session.getInstance argument
			Authenticator auth = new Authenticator() {
				// override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			};
			Session session = Session.getInstance(props, auth);

			EmailUtil.sendEmail(session, fromEmail, email, subject, body);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LibrarySystemException("Internal Error Try Again Later !");
		}

	}
}
