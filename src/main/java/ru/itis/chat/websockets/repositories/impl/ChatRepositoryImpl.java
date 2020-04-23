package ru.itis.chat.websockets.repositories.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.websockets.models.Room;
import ru.itis.chat.websockets.repositories.interfaces.ChatRepository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
class ChatRepositoryImpl implements ChatRepository {

    private static final String FIND_BY_ID =
            "select room from Room room where room.id = :id";

    private static final String FIND_ALL =
            "select room from Room room";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Room> find(long id) {
        TypedQuery<Room> query = entityManager.createQuery(FIND_BY_ID, Room.class);
        query.setParameter("id", id);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Room> findAll() {
        return entityManager.createQuery(FIND_ALL, Room.class).getResultList();
    }

    @Override
    public Room save(Room room) {
        return entityManager.merge(room);
    }
}
