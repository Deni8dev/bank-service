package com.slmndr.services.repositories;

import com.slmndr.entities.Account;
import com.slmndr.services.AccountService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AccountRepository implements AccountService {

    private final SessionFactory sessionFactory;

    @Autowired
    public AccountRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Account> findAll() {
        final Session session = this.sessionFactory.getCurrentSession();
        final TypedQuery<Account> query = session.createQuery("FROM accounts", Account.class);
        return query.getResultList();
    }
}
