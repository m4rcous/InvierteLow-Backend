package com.example.inviertelow.platform.iam.domain.model.commands;

import com.example.inviertelow.platform.iam.domain.model.valueObjects.Email;

public record SignupCommand(Email email, String password) {
}
