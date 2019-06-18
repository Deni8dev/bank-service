package com.slmndr.services.repositories;

import com.slmndr.entities.Account;
import com.slmndr.services.AccountService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
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

    @Override
    public Account findByUserId(Integer userId) {
        final Session session = this.sessionFactory.getCurrentSession();
        final TypedQuery<Account> query = session.createQuery("FROM accounts WHERE user_id = :userId", Account.class);
        query.setParameter("userId", userId);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
