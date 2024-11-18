package com.example.inviertelow.platform.iam.interfaces.rest.transform;

import com.example.inviertelow.platform.iam.domain.model.commands.SignupCommand;
import com.example.inviertelow.platform.iam.domain.model.valueObjects.Email;
import com.example.inviertelow.platform.iam.interfaces.rest.resources.SignupResource;

public class SignupCommandFromResourceAssembler {

    public static SignupCommand toCommandFromResource(SignupResource resource) {
        return new SignupCommand(new Email(resource.email()), resource.password());
    }
}