package ru.itis.chat.websockets.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {
    private final String sender;
    private final String text;
}
