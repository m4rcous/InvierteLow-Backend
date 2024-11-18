package com.example.inviertelow.platform.iam.interfaces.rest;

import com.example.inviertelow.platform.iam.application.commandservices.LoginCommandServiceImpl;
import com.example.inviertelow.platform.iam.application.commandservices.SignupCommandServiceImpl;
import com.example.inviertelow.platform.iam.interfaces.rest.resources.LoginResource;
import com.example.inviertelow.platform.iam.interfaces.rest.resources.SignupResource;
import com.example.inviertelow.platform.iam.interfaces.rest.transform.LoginCommandFromResourceAssembler;
import com.example.inviertelow.platform.iam.interfaces.rest.transform.SignupCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/auth", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public class AuthController {

    private final SignupCommandServiceImpl signupCommandService;
    private final LoginCommandServiceImpl loginCommandService;

    public AuthController(SignupCommandServiceImpl signupCommandService, LoginCommandServiceImpl loginCommandService) {
        this.signupCommandService = signupCommandService;
        this.loginCommandService = loginCommandService;
    }

    @PostMapping("/signup")
    @Operation(summary = "Register a new user")
    public ResponseEntity<Void> signup(@RequestBody SignupResource signupResource) {
        var command = SignupCommandFromResourceAssembler.toCommandFromResource(signupResource);
        signupCommandService.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    @Operation(summary = "Login and retrieve a JWT token")
    public ResponseEntity<String> login(@RequestBody LoginResource loginResource) {
        var command = LoginCommandFromResourceAssembler.toCommandFromResource(loginResource);
        String token = loginCommandService.handle(command);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Access granted!");
    }
}