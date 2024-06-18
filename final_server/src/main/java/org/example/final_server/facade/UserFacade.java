package org.example.final_server.facade;

import org.example.final_server.dto.request.UserRequest;
import org.example.final_server.dto.response.UserResponse;
import org.example.final_server.entity.user.User;

public interface UserFacade {
    UserResponse getUserByEmail(String email);
    void attachCarVariantToUser(Long userId, Long carVariantId);
}
