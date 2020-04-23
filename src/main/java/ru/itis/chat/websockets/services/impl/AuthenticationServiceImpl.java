package ru.itis.chat.websockets.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import ru.itis.chat.websockets.models.User;
import ru.itis.chat.websockets.services.interfaces.AuthenticationService;
import ru.itis.chat.websockets.services.interfaces.SessionService;

import java.util.Optional;

@Service
@AllArgsConstructor
class AuthenticationServiceImpl implements AuthenticationService {

    private final SessionService sessionService;

    @Nullable
    private User getNullableUser() {
        return (User) sessionService.getAttribute("user");
    }

    @Override
    public Optional<User> getUserOptional() {
        return Optional.ofNullable(getNullableUser());
    }

    @Override
    public User getUser() {
        return getUserOptional().orElseThrow();
    }

    @Override
    public boolean isAuthenticated() {
        return getNullableUser() != null;
    }


    @Override
    public void authenticate(User user) {
        sessionService.setAttribute("user", user);
    }

    @Override
    public void logout() {
        sessionService.removeAttribute("user");
    }
}
