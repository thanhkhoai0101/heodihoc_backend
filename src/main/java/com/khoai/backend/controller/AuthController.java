package com.khoai.backend.controller;

import com.khoai.backend.dto.LoginRequest;
import com.khoai.backend.dto.LoginResponse;
import com.khoai.backend.model.LoginSession;
import com.khoai.backend.model.User;
import com.khoai.backend.repository.LoginSessionRepository;
import com.khoai.backend.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final LoginSessionRepository loginSessionRepository;

    public AuthController(PasswordEncoder passwordEncoder, UserRepository userRepository,
                          LoginSessionRepository loginSessionRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.loginSessionRepository = loginSessionRepository;
    }

    @PostMapping("login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isEmpty()) {
            return null;
        }

        if (!passwordEncoder.matches(request.getPassword(), user.get().getPasswordHash())) {
            return null;
        }
        String token = UUID.randomUUID().toString();
        LoginSession session = new LoginSession();
        session.setUser(user.get());
        session.setToken(token);
        loginSessionRepository.save(session);

        return LoginResponse.builder()
                .user(user.get())
                .token(token)
                .build();
    }

    @GetMapping("check-login")
    public void checkLogin() {

    }
}
