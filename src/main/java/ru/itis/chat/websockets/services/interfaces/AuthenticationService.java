package ru.itis.chat.websockets.services.interfaces;

import org.springframework.lang.Nullable;
import ru.itis.chat.websockets.models.User;

import java.util.Optional;

public interface AuthenticationService {


     Optional<User> getUserOptional();

     User getUser();

     boolean isAuthenticated();


     void authenticate(User user);

     void logout();
}
