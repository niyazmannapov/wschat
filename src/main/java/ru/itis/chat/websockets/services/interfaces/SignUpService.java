package ru.itis.chat.websockets.services.interfaces;

import ru.itis.chat.websockets.dto.AuthDto;

public interface SignUpService {


    void signUp(AuthDto dto);
}
