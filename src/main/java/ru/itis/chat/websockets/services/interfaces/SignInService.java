package ru.itis.chat.websockets.services.interfaces;

import ru.itis.chat.websockets.dto.AuthDto;

public interface SignInService {

    void signIn(AuthDto dto);
}
