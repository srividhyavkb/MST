package Resources;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class SendEmailJava {
	
	public static String path="";
	
	public static void getReportPath(String reportPath)
	{
		path = reportPath;
	}

	public static void sendEmail() throws EmailException {

		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(path);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Test Execution Report");
		attachment.setName("Report");
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("phoenixfire15041996@gmail.com", "phoenix@2021"));
		email.setSSLOnConnect(true);
		email.setFrom("phoenixfire15041996@gmail.com");
		email.setSubject("See Test Mobile Web Automation Report");
//		email.setMsg("This is a test mail from Selenium");
		email.attach(attachment);
		String[] emailAddress = { "baulsom1596@gmail.com", "Somnath.Baul@brinker.com" };
		email.addTo(emailAddress);
		email.send();
		System.out.println("===========Email Sent============");

	}

}
