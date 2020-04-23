package ru.itis.chat.websockets.dto;

import lombok.Data;

@Data
public class AuthDto {
    private final String username;
    private final String password;
}
