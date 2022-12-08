package com.kata.crud.dao;

import com.kata.crud.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        List<User> list = entityManager.createQuery("FROM User", User.class).getResultList();

        return list;
    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUser(int id) {
        User user = entityManager.find(User.class, id);

        return user;
    }

    @Override
    public void deleteUser(int id) {
        Query query = entityManager.createQuery("DELETE FROM User WHERE id=:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }
}
