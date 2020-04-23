package ru.itis.chat.websockets.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.chat.websockets.dto.AuthDto;
import ru.itis.chat.websockets.models.User;
import ru.itis.chat.websockets.repositories.interfaces.UserRepository;
import ru.itis.chat.websockets.services.interfaces.AuthenticationService;
import ru.itis.chat.websockets.services.interfaces.SignInService;

@Service
@AllArgsConstructor
class SignInServiceImpl implements SignInService {

    private final BCryptPasswordEncoder encoder;
    private final AuthenticationService authenticationService;

    private final UserRepository userRepository;

    @Override
    public void signIn(AuthDto dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(IllegalStateException::new);
        authenticationService.authenticate(user);

        if (!encoder.matches(dto.getPassword(), user.getHashPassword())) {
            throw new AccessDeniedException("Доступ запрещен");
        }
    }
}
