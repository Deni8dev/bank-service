package com.slmndr.services.repositories;

import com.slmndr.entities.User;
import com.slmndr.services.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
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
}
