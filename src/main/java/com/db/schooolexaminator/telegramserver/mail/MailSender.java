package com.db.schooolexaminator.telegramserver.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

@Component
@PropertySource("classpath:mail.properties")
public class MailSender {

    @Value("${senderEmail}")
    private String senderEmailID;

    @Value("${senderPassword}")
    private String senderPassword;

    @Value("${emailSMTPserver}")
    private String emailSMTPserver;

    @Value("${emailServerPort}")
    private String emailServerPort;


    private Properties props;


    @PostConstruct
    public void init() {
        props = new Properties();
        props.put("mail.smtp.user",senderEmailID);
        props.put("mail.smtp.host", emailSMTPserver);
        props.put("mail.smtp.port", emailServerPort);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", emailServerPort);
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
    }


    public void sendEmail(String receiverEmailID, String emailSubject, String emailBody) {
        try
        {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.setText(emailBody);
            msg.setSubject(emailSubject);
            msg.setFrom(new InternetAddress(senderEmailID));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(receiverEmailID));
            Transport.send(msg);
            System.out.println("Message to " + receiverEmailID + " send Successfully");
        }
        catch (Exception mex)
        {
            mex.printStackTrace();
        }
    }


    public class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(senderEmailID, senderPassword);
        }
    }
}