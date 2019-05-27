package com.slmndr.services.repositories;

import com.slmndr.entities.User;
import com.slmndr.services.LoginService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
@Transactional
public class LoginRepository implements LoginService {

    private final SessionFactory sessionFactory;

    @Autowired
    public LoginRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Boolean login(String username, String password) {
        final Session session = this.sessionFactory.getCurrentSession();
        final TypedQuery<User> query =
            session.createQuery("FROM users WHERE username = :username AND password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getResultList().size() == 1;
    }
}
