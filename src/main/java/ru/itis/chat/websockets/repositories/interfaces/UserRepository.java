package ru.itis.chat.websockets.repositories.interfaces;

import ru.itis.chat.websockets.models.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    User save(User user);
}
