package com.example.inviertelow.platform.iam.infrastructure.acl;

import com.example.inviertelow.platform.iam.domain.model.aggregates.User;
import com.example.inviertelow.platform.iam.domain.model.entities.Role;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AccessControlList {

    public boolean canAccessResource(User user, String resource, String action) {
        // Obtener los roles del usuario
        Set<Role> roles = user.getRoles();

        // Lógica para verificar acceso según roles, recursos y acciones
        for (Role role : roles) {
            if (hasPermission(role, resource, action)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPermission(Role role, String resource, String action) {
        // Aquí puedes implementar lógica específica, por ejemplo:
        // Si el rol es ADMIN, permitir acceso completo
        if ("ADMIN".equals(role.getName())) {
            return true;
        }

        // Lógica personalizada para cada rol y acción
        if ("USER".equals(role.getName()) && "READ".equals(action) && "profile".equals(resource)) {
            return true;
        }

        // Denegar acceso por defecto
        return false;
    }
}