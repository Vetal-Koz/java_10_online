package org.example.final_server.facade.impl;


import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.request.AuthRequest;
import org.example.final_server.entity.user.User;
import org.example.final_server.facade.AuthFacade;
import org.example.final_server.service.UserService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthFacadeImpl implements AuthFacade {

    private final UserService userService;

    @Override
    public void register(AuthRequest entity) {
        User user = new User();
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        userService.create(user);
    }

    @Override
    public void login(String email, String password) {

    }
}
