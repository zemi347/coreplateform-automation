
package helper;


import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

public class verifyEmail{

public static boolean verifyMail(String message) {
		Folder folder = null;
		Store  store  = null;

		System.out.println("***READING MAILBOX...");
		try {
			  Properties prop = Configutil.configdatafile("Config.Properties");
			  String ReceiverEmail =prop.getProperty("ReceiverEmail");
			  String RecevierPassword =prop.getProperty("RecevierPassword");



			Properties props = new Properties();
			props.put("mail.store.protocol", "imaps");
			Session session = Session.getInstance(props);
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", ReceiverEmail, RecevierPassword);
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			Message[] messages = folder.getMessages();
			System.out.println("No of Messages : " + folder.getMessageCount());
			System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
			for (int i = 0; i < messages.length; i++) {
				System.out.println("Reading MESSAGE # " + (i + 1) + "...");
				Message msg = messages[i];
				String strMailSubject;
				String strMailBody;
				// Getting mail subject
				Object subject = msg.getSubject();
				// Getting mail body
				Object content = msg.getContent();
				// Casting objects of mail subject and body into String
				strMailSubject = (String) subject;
				strMailBody = (String) content;
				//---- This is what you want to do------
				if (strMailSubject.contains(message)||strMailBody.contains(message))
				{
					System.out.println(strMailSubject );
					System.out.println(strMailBody);
					break;
				}
			}
			return true;
		} catch (MessagingException | IOException messagingException) {
			messagingException.printStackTrace();
		} finally {
			if (folder != null) {
				try {
					folder.close(true);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
			if (store != null) {
				try {
					store.close();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}