package ru.itis.chat.websockets.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.chat.websockets.services.interfaces.AuthenticationService;

@Controller
@AllArgsConstructor
public class HomeController {

    private final AuthenticationService authService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("home", "authenticated", authService.isAuthenticated());
    }
}
