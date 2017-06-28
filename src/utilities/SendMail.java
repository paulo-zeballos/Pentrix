package utilities;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public final class SendMail {

	public static void main(String[] args) {
		// create some properties and get the default Session
		final String username = "s3568672pentrix@gmail.com";
		final String password = "s3568672";
		
		String msgText = "hola!!!!!!!!!!!";
		String from = "s3568672pentrix@gmail.com";
		String to= "paulo.zeballosflores@outlook.com";
		
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username,password);
					}
				});

		try {
		    // create a message
		    MimeMessage msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress(from));
		    //InternetAddress[] address = {new InternetAddress(to)};
		    msg.setRecipients(Message.RecipientType.TO , InternetAddress.parse(to));
		    msg.setSubject("Pentrix!!");
		    msg.setSentDate(new Date());
		    // If the desired charset is known, you can use
		    // setText(text, charset)
		    msg.setText(msgText);
		   
		    Transport.send(msg);
			System.out.println("Done");
			
			
		}  catch (MessagingException ex) {
			System.err.println("Cannot send email. " + ex);
		}
		
	}
}
