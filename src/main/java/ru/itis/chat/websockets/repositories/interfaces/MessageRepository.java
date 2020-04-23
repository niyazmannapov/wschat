package ru.itis.chat.websockets.repositories.interfaces;

import ru.itis.chat.websockets.models.Message;
import ru.itis.chat.websockets.models.Room;

import java.util.List;

public interface MessageRepository {

    Message save(Message message);

    List<Message> findByRoom(Room room);
}
