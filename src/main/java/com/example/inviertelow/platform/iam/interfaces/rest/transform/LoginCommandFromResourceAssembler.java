package com.example.inviertelow.platform.iam.interfaces.rest.transform;

import com.example.inviertelow.platform.iam.domain.model.commands.LoginCommand;
import com.example.inviertelow.platform.iam.interfaces.rest.resources.LoginResource;

public class LoginCommandFromResourceAssembler {

    public static LoginCommand toCommandFromResource(LoginResource resource) {
        return new LoginCommand(resource.email(), resource.password());
    }
}