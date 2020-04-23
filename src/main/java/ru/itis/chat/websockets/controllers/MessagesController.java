package ru.itis.chat.websockets.controllers;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.itis.chat.websockets.dto.MessageDto;
import ru.itis.chat.websockets.dto.SendMessageDto;
import ru.itis.chat.websockets.services.interfaces.AuthenticationService;
import ru.itis.chat.websockets.services.interfaces.ChatService;

@Controller
@AllArgsConstructor
public class MessagesController {

    private final ChatService chatService;
    private final AuthenticationService authService;

    @MessageMapping("/chat/{id}")
    @SendTo("/topic/chat/{id}")
    public MessageDto sendMessage(@DestinationVariable long id, SendMessageDto dto) {
        if (authService.isAuthenticated()) {
            return chatService.send(id, dto);
        }
        else throw new IllegalStateException();
    }
}
