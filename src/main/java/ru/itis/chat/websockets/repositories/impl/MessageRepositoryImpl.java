package ru.itis.chat.websockets.repositories.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.websockets.models.Message;
import ru.itis.chat.websockets.models.Room;
import ru.itis.chat.websockets.repositories.interfaces.MessageRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
class MessageRepositoryImpl implements MessageRepository {

    public static final String FIND_BY_ROOM =
            "select message from Message message " +
                    "join Room room on message.room = room " +
                    "where room.id = :id " +
                    "order by message.id";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Message save(Message message) {
        return entityManager.merge(message);
    }

    @Override
    public List<Message> findByRoom(Room room) {
        TypedQuery<Message> query = entityManager.createQuery(FIND_BY_ROOM, Message.class);
        query.setParameter("id", room.getId());
        System.out.println(query.getResultList());
        return query.getResultList();
    }
}
