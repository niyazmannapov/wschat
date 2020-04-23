package ru.itis.chat.websockets.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.websockets.dto.MessageDto;
import ru.itis.chat.websockets.dto.SendMessageDto;
import ru.itis.chat.websockets.models.Message;
import ru.itis.chat.websockets.models.Room;
import ru.itis.chat.websockets.models.User;
import ru.itis.chat.websockets.repositories.interfaces.MessageRepository;
import ru.itis.chat.websockets.repositories.interfaces.ChatRepository;
import ru.itis.chat.websockets.services.interfaces.AuthenticationService;
import ru.itis.chat.websockets.services.interfaces.ChatService;

@Service
@AllArgsConstructor
class ChatServiceImpl implements ChatService {

    private final AuthenticationService authService;
    private final ChatRepository roomRepository;
    private final MessageRepository messageRepository;


    @Override
    public MessageDto dtoFrom(Message message) {
        return MessageDto.builder()
                .sender(message.getUser().getUsername())
                .text(message.getText())
                .build();
    }

    @Override
    @Transactional
    public MessageDto send(long id, SendMessageDto dto) {
        Room room = roomRepository.find(id).orElseThrow();
        User user = authService.getUser();

        Message message = messageRepository.save(Message.builder()
                .room(room)
                .user(user)
                .text(dto.getText())
                .build());

        return dtoFrom(message);
    }
}
