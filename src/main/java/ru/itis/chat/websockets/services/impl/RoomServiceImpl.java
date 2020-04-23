package ru.itis.chat.websockets.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chat.websockets.dto.ChatRoomDto;
import ru.itis.chat.websockets.dto.CreateChatRoomDto;
import ru.itis.chat.websockets.models.Room;
import ru.itis.chat.websockets.repositories.interfaces.MessageRepository;
import ru.itis.chat.websockets.repositories.interfaces.ChatRepository;
import ru.itis.chat.websockets.services.interfaces.RoomService;
import ru.itis.chat.websockets.services.interfaces.ChatService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class RoomServiceImpl implements RoomService {

    private final ChatService chatService;

    private final ChatRepository roomRepository;
    private final MessageRepository messageRepository;

    @Override
    public void createRoom(CreateChatRoomDto dto) {
        roomRepository.save(Room.builder()
                .name(dto.getName())
                .build());
    }

    @Override
    public List<ChatRoomDto> getRooms() {
        return roomRepository.findAll().stream()
                .map(ChatRoomDto::from)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public ChatRoomDto getRoom(Long roomId) {
        Room room = roomRepository.find(roomId).orElseThrow();
        List messages = messageRepository.findByRoom(room).stream()
                .map(chatService::dtoFrom)
                .collect(Collectors.toList());

        return ChatRoomDto.builder()
                .id(room.getId())
                .name(room.getName())
                .messages(messages)
                .build();
    }
}
