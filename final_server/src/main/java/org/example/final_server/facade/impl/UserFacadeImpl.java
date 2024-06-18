package org.example.final_server.facade.impl;

import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.request.UserRequest;
import org.example.final_server.dto.response.UserResponse;
import org.example.final_server.entity.user.User;
import org.example.final_server.facade.UserFacade;
import org.example.final_server.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    @Override
    public UserResponse getUserByEmail(String email) {
        return new UserResponse(userService.findUserByEmail(email));
    }

    @Override
    public void attachCarVariantToUser(Long userId, Long carVariantId) {
        userService.attachCarVariantToUser(userId, carVariantId);
    }
}
