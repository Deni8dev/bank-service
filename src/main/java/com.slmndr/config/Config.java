package com.slmndr.config;

import com.slmndr.mail.EmailSender;
import com.slmndr.services.AccountService;
import com.slmndr.services.LoginService;
import com.slmndr.services.TransactionService;
import com.slmndr.services.UserService;
import com.slmndr.services.repositories.AccountRepository;
import com.slmndr.services.repositories.LoginRepository;
import com.slmndr.services.repositories.TransactionRepository;
import com.slmndr.services.repositories.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class Config {

    @Value("${mail.smtp.auth}")
    private Boolean mailSmtpAuth;

    @Value("${mail.smtp.starttls.enable}")
    private Boolean mailSmtpStarttls;

    @Value("${mail.smtp.host}")
    private String mailSmtpHost;

    @Value("${mail.smtp.port}")
    private Integer mailSmtpPort;

    @Value("${mail.smtp.ssl.trust}")
    private String mailSmtpSSLTrust;

    @Value("${email.username}")
    private String emailUsername;

    @Value("${email.password}")
    private String emailPassword;


    @Bean
    public UserService userService(final SessionFactory sessionFactory) {
        return new UserRepository(sessionFactory);
    }

    @Bean
    public AccountService accountService(final SessionFactory sessionFactory) {
        return new AccountRepository(sessionFactory);
    }

    @Bean
    public LoginService loginService(final SessionFactory sessionFactory) {
        return new LoginRepository(sessionFactory);
    }

    @Bean
    public TransactionService transactionService(final SessionFactory sessionFactory) {
        return new TransactionRepository(sessionFactory);
    }

    @Bean
    public Session session() {
        final Properties props = new Properties();
        props.put("mail.smtp.auth", this.mailSmtpAuth);
        props.put("mail.smtp.starttls.enable", this.mailSmtpStarttls);
        props.put("mail.smtp.host", this.mailSmtpHost);
        props.put("mail.smtp.port", this.mailSmtpPort);
        props.put("mail.smtp.ssl.trust", this.mailSmtpSSLTrust);

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailUsername, emailPassword);
            }
        });
    }

    @Bean
    public EmailSender emailSender(final Session session) {
        return new EmailSender(session, this.emailUsername);
    }
}
