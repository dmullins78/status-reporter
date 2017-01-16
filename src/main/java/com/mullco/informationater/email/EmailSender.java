package com.mullco.informationater.email;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
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

    public void sendIt(Map<String, String> params) {
        try {
            Properties mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");

            Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);

            InternetAddress[] toAddresses = InternetAddress.parse(params.get("to"));

            MimeMessage generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipients(Message.RecipientType.TO, toAddresses);
            generateMailMessage.addFrom(new Address[] { new InternetAddress(params.get("from"))});
            generateMailMessage.setSubject(params.get("subject"));

            generateMailMessage.setContent(params.get("body"), "text/html");

            Transport transport = getMailSession.getTransport("smtp");
            transport.connect(emailHost, emailUid, emailPwd);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
