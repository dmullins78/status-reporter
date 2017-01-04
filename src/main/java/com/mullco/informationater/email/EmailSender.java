package com.mullco.informationater.email;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    private final String emailHost;
    private final String emailUid;
    private final String emailPwd;

    public EmailSender(String emailHost, String emailUid, String emailPwd) {
        this.emailHost = emailHost;
        this.emailUid = emailUid;
        this.emailPwd = emailPwd;
    }

    public void sendIt(String emailBody) {
        try {
            Properties mailServerProperties;
            Session getMailSession;
            MimeMessage generateMailMessage;

            System.out.println("\n 1st ===> setup Mail Server Properties..");
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");
            System.out.println("Mail Server Properties have been setup successfully..");

            // Step2
            System.out.println("\n\n 2nd ===> get Mail Session..");
            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("dmullins78@gmail.com"));
            generateMailMessage.setSubject("Greetings from Crunchify..");

            generateMailMessage.setContent(emailBody, "text/html");
            System.out.println("Mail Session has been created successfully..");

            // Step3
            System.out.println("\n\n 3rd ===> Get Session and Send mail");
            Transport transport = getMailSession.getTransport("smtp");

            transport.connect(emailHost, emailUid, emailPwd);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
