package ru.itis.chat.websockets.services.interfaces;

import ru.itis.chat.websockets.dto.ChatRoomDto;

import ru.itis.chat.websockets.dto.CreateChatRoomDto;

import java.util.List;

public interface RoomService {

    void createRoom(CreateChatRoomDto dto);

    List<ChatRoomDto> getRooms();

    ChatRoomDto getRoom(Long id);
}
