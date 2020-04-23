package ru.itis.chat.websockets.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.chat.websockets.dto.CreateChatRoomDto;
import ru.itis.chat.websockets.services.interfaces.RoomService;

@Controller
@AllArgsConstructor
public class ChatController {


    private final RoomService roomService;

    @GetMapping("/chat")
    public String getRooms(ModelMap map) {
        map.put("rooms", roomService.getRooms());
        return "rooms";
    }

    @PostMapping("/chat")
    public String createRoom(CreateChatRoomDto dto) {
        roomService.createRoom(dto);
        return "redirect:/chat";
    }

    @GetMapping("/chat/{id}")
    public String getChat(@PathVariable String id, ModelMap map) {
        map.put("room", roomService.getRoom(Long.parseLong(id)));
        return "chat";
    }
}
