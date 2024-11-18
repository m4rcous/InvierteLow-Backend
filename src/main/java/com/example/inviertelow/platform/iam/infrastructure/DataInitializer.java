package com.example.inviertelow.platform.iam.infrastructure;

import com.example.inviertelow.platform.iam.domain.model.entities.Role;
import com.example.inviertelow.platform.iam.infrastructure.repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        // Inicializa el rol USER si no existe
        if (roleRepository.findByName("USER").isEmpty()) {
            roleRepository.save(new Role("USER"));
        }

        // Opcional: Inicializa otros roles como ADMIN si es necesario
        if (roleRepository.findByName("ADMIN").isEmpty()) {
            roleRepository.save(new Role("ADMIN"));
        }
    }
}