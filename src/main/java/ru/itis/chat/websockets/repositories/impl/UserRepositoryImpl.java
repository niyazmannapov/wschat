package ru.itis.chat.websockets.repositories.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.websockets.models.User;
import ru.itis.chat.websockets.repositories.interfaces.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
@Transactional
class UserRepositoryImpl implements UserRepository {

    private static final String FIND_BY_USERNAME =
            "select u from User u where u.username = :username";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(FIND_BY_USERNAME, User.class);
        query.setParameter("username", username);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public User save(User user) {
        return entityManager.merge(user);
    }
}
