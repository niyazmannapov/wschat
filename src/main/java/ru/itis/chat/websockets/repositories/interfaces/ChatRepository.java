package ru.itis.chat.websockets.repositories.interfaces;

import ru.itis.chat.websockets.models.Room;

import java.util.List;
import java.util.Optional;

public interface ChatRepository {

    Optional<Room> find(long id);

    List<Room> findAll();

    Room save(Room room);
}
