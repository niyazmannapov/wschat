package ru.itis.chat.websockets.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.chat.websockets.dto.AuthDto;
import ru.itis.chat.websockets.models.User;
import ru.itis.chat.websockets.repositories.interfaces.UserRepository;
import ru.itis.chat.websockets.services.interfaces.AuthenticationService;
import ru.itis.chat.websockets.services.interfaces.SignUpService;

@Service
@AllArgsConstructor
class SignUpServiceImpl implements SignUpService {

    private final AuthenticationService authenticationService;
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    @Override
    public void signUp(AuthDto dto) {
        final String username = dto.getUsername();
        final String password = dto.getPassword();


        User user = userRepository.save(User.builder()
                .username(username)
                .hashPassword(encoder.encode(password))
                .build());

        authenticationService.authenticate(user);
    }
}
