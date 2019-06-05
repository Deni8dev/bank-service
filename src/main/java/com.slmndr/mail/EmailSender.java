package com.slmndr.mail;

import com.slmndr.entities.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {

    private final Session session;
    private final String fromEmail;

    public EmailSender(final Session session, final String fromEmail) {
        this.session = session;
        this.fromEmail = fromEmail;
    }

    public void sendEmail(final String toEmail, final User data) throws MessagingException {
        Transport.send(this.buildMessage(toEmail, data));
    }

    private Message buildMessage(final String toEmail, final User data) throws MessagingException {
        final Message message = new MimeMessage(this.session);
        final String msg = "" +
            "Your Salamandra Account has been created successfully." +
            "<br/>" +
            "Dont forget change your password.<br/><br/>" +
            "<b>Username</b>: " + data.getUsername() +
            "<b>Password</b>: " + data.getPassword();

        final MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        final Multipart multipart = new MimeMultipart();

        message.setFrom(new InternetAddress(this.fromEmail));
        message.setSubject("Salamandra Account created!");
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

        return message;
    }
}
