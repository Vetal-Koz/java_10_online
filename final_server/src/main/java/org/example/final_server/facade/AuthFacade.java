package org.example.final_server.facade;

import org.example.final_server.dto.request.AuthRequest;

public interface AuthFacade {

    void register(AuthRequest entity);
    void login(String email, String password);
}
