package ru.itis.chat.websockets.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.chat.websockets.models.Room;

import java.util.List;

@Data
@Builder
public class ChatRoomDto {
    private final String name;
    private final long id;
    private final List<MessageDto> messages;

    public static ChatRoomDto from(Room room) {
        return builder()
                .name(room.getName())
                .id(room.getId())
                .build();
    }
}
