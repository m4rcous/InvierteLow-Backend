package com.example.inviertelow.platform.iam.application.commandservices;

import com.example.inviertelow.platform.iam.domain.model.aggregates.User;
import com.example.inviertelow.platform.iam.domain.model.commands.AssignRoleCommand;
import com.example.inviertelow.platform.iam.domain.model.commands.SignupCommand;
import com.example.inviertelow.platform.iam.domain.model.entities.Role;
import com.example.inviertelow.platform.iam.domain.model.valueObjects.Email;
import com.example.inviertelow.platform.iam.domain.model.valueObjects.Password;
import com.example.inviertelow.platform.iam.domain.services.HashingService;
import com.example.inviertelow.platform.iam.infrastructure.repositories.RoleRepository;
import com.example.inviertelow.platform.iam.infrastructure.repositories.UserRepository;
import com.example.inviertelow.platform.iam.infrastructure.security.PasswordHasher;
import org.springframework.stereotype.Service;

@Service
public class SignupCommandServiceImpl {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final AssignRoleCommandServiceImpl assignRoleCommandService;

    public SignupCommandServiceImpl(UserRepository userRepository, HashingService hashingService,
                                    AssignRoleCommandServiceImpl assignRoleCommandService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.assignRoleCommandService = assignRoleCommandService;
    }

    public void handle(SignupCommand command) {
        // Verificar si el correo ya está en uso
        if (userRepository.existsByEmail(new Email(command.email().getValue()))) {
            throw new RuntimeException("Email is already in use");
        }

        // Crear el usuario con email y contraseña
        var email = new Email(command.email().getValue());
        var password = new Password(command.password(), hashingService);
        var user = new User(email, password);

        // Guardar el usuario
        userRepository.save(user);

        // Asignar automáticamente el rol 'USER' al usuario recién creado
        assignRoleCommandService.handle(new AssignRoleCommand(user.getId(), "USER"));
    }
}