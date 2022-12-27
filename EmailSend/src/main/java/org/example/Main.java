package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws MessagingException {
        System.out.println("Preparing to send message...");
        String message = "Hello Dear, This is message for security check.";
        String subject = "CodersArea : Confirmation";
        String to = "divyeshvadher1010@gmail.com";
        String from = "divyeshvadher782@gmail.com";

       // sendEmail(message,subject,to,from);
        sendAttach(message,subject,to,from);
    }

    private static void sendAttach(String message, String subject, String to, String from) throws MessagingException {
        //variable for gmail
        String host="smtp.gmail.com";

        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES "+properties);

        //setting important information to properties object

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        //Step 1: to get the session object...
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("divyeshvadher782@gmail.com", "bnkzgoacdnaimyts");
            }



        });

        session.setDebug(true);

        //Step 2 : compose the message [text,multi media]
        MimeMessage m = new MimeMessage(session);

        try {

            //from email
            m.setFrom(from);

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);


            //attachement..

            //file path
            String path="C:\\Users\\Divyesh\\Pictures\\refresh.png";


            MimeMultipart mimeMultipart = new MimeMultipart();
            //text
            //file

            MimeBodyPart textMime = new MimeBodyPart();

            MimeBodyPart fileMime = new MimeBodyPart();

            try {

                textMime.setText(message);

                File file=new File(path);
                fileMime.attachFile(file);


                mimeMultipart.addBodyPart(textMime);
                mimeMultipart.addBodyPart(fileMime);
            } catch (Exception e) {

                e.printStackTrace();
            }
            m.setContent(mimeMultipart);
            //send

            //Step 3 : send the message using Transport class
            Transport.send(m);

        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Sent success...................");


    }

    //this is message is responsible to send email...
/*
    private static void sendEmail(String message, String subject, String to, String from) throws MessagingException {

        //variable for gmail
        String host = "smtp.gmail.com";

        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("Properties " + properties);

        //setting important information to property object

        //host set

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //step 1: to get the session object...
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("divyeshvadher782@gmail.com","bnkzgoacdnaimyts");
            }
        });

        session.setDebug(true);
        //step 2: compose the message [text, multi-media]

        MimeMessage m = new MimeMessage(session);

        //from email
        try {
            m.setFrom(from);

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);

            //adding text to message
            m.setText(message);

            //send

            //step 3: send the message using transport class
            Transport.send(m);

            System.out.println("Send Successfully...");

        }catch (Exception e){
            e.printStackTrace();
        }
    }*/
}