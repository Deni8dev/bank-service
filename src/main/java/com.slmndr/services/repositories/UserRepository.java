package com.slmndr.services.repositories;

import com.slmndr.util.Generator;
import com.slmndr.entities.Account;
import com.slmndr.entities.User;
import com.slmndr.services.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepository implements UserService {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAll() {
        final Session session = this.sessionFactory.getCurrentSession();
        final TypedQuery<User> query = session.createQuery("FROM users", User.class);
        return query.getResultList();
    }

    @Override
    public User find(String username) {
        final Session session = this.sessionFactory.getCurrentSession();
        final TypedQuery<User> query = session.createQuery("FROM users WHERE username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public Boolean save(User user) {
        final Session session = this.sessionFactory.getCurrentSession();
        session.save(user);
        final Account account = new Account();
        account.setUserId(user.getId());
        account.setAccountNumber(Generator.accountNumberGenerator());
        account.setBalance(10000);
        session.save(account);
        System.out.println(user.toString());
        return true;
    }
}
