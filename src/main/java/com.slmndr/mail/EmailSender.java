package com.slmndr.mail;

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

    public void sendEmail(final String toEmail) throws MessagingException {
        Transport.send(this.buildMessage(toEmail));
    }

    private Message buildMessage(final String toEmail) throws MessagingException {
        final Message message = new MimeMessage(this.session);
        final String msg = "Your Salamandra Account has been created successfully.";

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
