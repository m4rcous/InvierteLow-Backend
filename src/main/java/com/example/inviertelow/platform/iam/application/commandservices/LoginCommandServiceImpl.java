package com.example.inviertelow.platform.iam.application.commandservices;

import com.example.inviertelow.platform.iam.domain.model.commands.LoginCommand;
import com.example.inviertelow.platform.iam.domain.model.entities.Role;
import com.example.inviertelow.platform.iam.domain.model.valueObjects.Email;
import com.example.inviertelow.platform.iam.infrastructure.repositories.UserRepository;
import com.example.inviertelow.platform.iam.infrastructure.security.JwtTokenProvider;
import com.example.inviertelow.platform.iam.infrastructure.security.PasswordHasher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LoginCommandServiceImpl {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordHasher passwordHasher;

    public LoginCommandServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordHasher = passwordHasher;
    }

    public String handle(LoginCommand command) {
        var user = userRepository.findByEmail(new Email(command.email()))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));


        if (!passwordHasher.matches(command.password(), user.getPassword().getHashedValue())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Crear los claims
        Map<String, Object> claims = Map.of(
                "id", user.getId(),
                "roles", user.getRoles().stream()
                        .map(role -> "ROLE_" + role.getName())
                        .toList()
        );

        return jwtTokenProvider.generateToken(claims);
    }
}