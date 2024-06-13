package org.example.final_server.service;

import org.example.final_server.dto.request.AuthRequest;
import org.example.final_server.dto.response.AuthResponse;

public interface AuthenticationService {

    AuthResponse register(AuthRequest authRequest);
    AuthResponse login(AuthRequest authRequest);
}
