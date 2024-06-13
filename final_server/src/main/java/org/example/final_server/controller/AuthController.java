package org.example.final_server.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.request.AuthRequest;
import org.example.final_server.dto.response.AuthResponse;
import org.example.final_server.dto.response.ResponseContainer;
import org.example.final_server.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "Authentication page", description = "the plp API with authentication methods")
@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<ResponseContainer<AuthResponse>> register(
            @Valid
            @RequestBody AuthRequest authRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseContainer<>(authenticationService.register(authRequest)));
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseContainer<AuthResponse>> login(
            @Valid
            @RequestBody AuthRequest authRequest){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseContainer<>(authenticationService.login(authRequest)));
    }


}
