package com.emailSend;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class App 
{
    public static void main( String[] args )
    {
        String msg = "Hello, This is message for security check";
        String sub = "Confirmation";
        String to = "divyeshvadher1010@outlook.com";
        String from = "divyeshvadher782@gmail.com";
        
        sendEmail(msg,sub,to,from);
    }

    //this is responsible for send email...
	private static void sendEmail(String msg, String sub, String to, String from) {
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES: "+properties);
		
		//setting important information to properties object
		//host set
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//step 1: to get the session object
		// Session session = Session.getInstance(properties);
		 Session session2 = Session.getInstance(properties, new javax.mail.Authenticator() {
			 	@Override
				protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
					return new javax.mail.PasswordAuthentication("divyeshvadher782@gmail.com","glbvsoyrlzaimqnn");
					//return new PasswordAuthentication(from,"bnkzgoacdnaimyts");
				}
		 });
		
		 session2.setDebug(true);
		//step 2: Compose the message
		MimeMessage m = new MimeMessage(session2);
		try {
			
		//from email
		m.setFrom(from);
		
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//adding subject to message
		m.setSubject(sub);
		
		//adding text to message
		m.setText(msg);
		
		//send
		
		//Step 3: send the message using Transport class
		Transport.send(m);
		
		System.out.println("Sent Successfully");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*protected static PasswordAuthentication PasswordAuthentication(String string, String string2) {
		return null;
	}*/
}
