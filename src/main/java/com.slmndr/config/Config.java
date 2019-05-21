package com.slmndr.config;

import com.slmndr.services.AccountService;
import com.slmndr.services.UserService;
import com.slmndr.services.repositories.AccountRepository;
import com.slmndr.services.repositories.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public UserService userService(final SessionFactory sessionFactory) {
        return new UserRepository(sessionFactory);
    }

    @Bean
    public AccountService accountService(final SessionFactory sessionFactory) {
        return new AccountRepository(sessionFactory);
    }
}
