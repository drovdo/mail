package com;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {

    private Session session;

    public MailService(final String username, final String password,
                       String host, String port, String auth, String starttls) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    public void send(String to, String sub, String text) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setRecipient(Message.RecipientType.TO,
                new InternetAddress(to));
        message.setSubject(sub);
        message.setText(text);

        Transport.send(message);
    }
}
