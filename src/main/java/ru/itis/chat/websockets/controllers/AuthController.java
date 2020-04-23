package ru.itis.chat.websockets.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.chat.websockets.dto.AuthDto;
import ru.itis.chat.websockets.services.interfaces.AuthenticationService;
import ru.itis.chat.websockets.services.interfaces.SignInService;
import ru.itis.chat.websockets.services.interfaces.SignUpService;

@Controller
@AllArgsConstructor
public class AuthController {

    private final SignInService signInService;
    private final SignUpService signUpService;
    private final AuthenticationService authService;

    @GetMapping("/signIn")
    public String getSignInPage() {
        if (authService.isAuthenticated()) {
            return "redirect:/";
        } else {
            return "sign_in";
        }
    }

    @PostMapping("/signIn")
    public String signIn(AuthDto dto) {
        if (!authService.isAuthenticated()) {
            signInService.signIn(dto);
        }

        return "redirect:/chat";
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        if (authService.isAuthenticated()) {
            return "redirect:/";
        } else {
            return "sign_up";
        }
    }

    @PostMapping("/signUp")
    public String signUp(AuthDto dto) {
        if (!authService.isAuthenticated()) {
            signUpService.signUp(dto);
        }

        return "redirect:/";
    }

    @RequestMapping("/signOut")
    public String signOut() {
        authService.logout();
        return "redirect:/";
    }
}
