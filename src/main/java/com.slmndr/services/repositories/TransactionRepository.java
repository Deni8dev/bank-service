package com.slmndr.services.repositories;

import com.slmndr.entities.Transaction;
import com.slmndr.services.TransactionService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class TransactionRepository implements TransactionService {

    private final SessionFactory sessionFactory;

    public TransactionRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Transaction> findAll(final Integer accountId) {
        final Session session = this.sessionFactory.getCurrentSession();
        final TypedQuery<Transaction> query = session
            .createQuery("FROM transactions WHERE id_account = :id_account", Transaction.class);
        query.setParameter("id_account", accountId);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }
}
