package ru.itis.chat.websockets.services.interfaces;

import ru.itis.chat.websockets.dto.MessageDto;
import ru.itis.chat.websockets.dto.SendMessageDto;
import ru.itis.chat.websockets.models.Message;

public interface ChatService {

    MessageDto dtoFrom(Message message);

    MessageDto send(long id, SendMessageDto dto);
}
