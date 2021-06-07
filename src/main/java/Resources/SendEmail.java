package Resources;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class SendEmail extends Thread {

	public static String reportPath;

	public static void getReportPath(File dir) {
		File[] files = dir.listFiles();
		Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
		for (File file : files) {

			reportPath = file.getAbsolutePath();
			break;
		}

	}

	public void run()  {

		// Create the attachment
		try {
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(reportPath);
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
		email.attach(attachment);
		String[] emailAddress = { "baulsom1596@gmail.com", "Somnath.Baul@brinker.com" };
		email.addTo(emailAddress);
		email.send();
		}
		catch(Exception e)
		{
			
		}

	}

}
