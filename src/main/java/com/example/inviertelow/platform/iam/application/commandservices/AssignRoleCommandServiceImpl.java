package com.example.inviertelow.platform.iam.application.commandservices;

import com.example.inviertelow.platform.iam.domain.model.commands.AssignRoleCommand;
import com.example.inviertelow.platform.iam.domain.model.entities.Role;
import com.example.inviertelow.platform.iam.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AssignRoleCommandServiceImpl {

    private final UserRepository userRepository;

    public AssignRoleCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void handle(AssignRoleCommand command) {
        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convertir el string del rol a un objeto Role
        var role = new Role(command.role());
        user.addRole(role);

        userRepository.save(user);
    }
}