package org.example.final_server.facade;

import org.example.final_server.dto.request.DataTableRequest;
import org.example.final_server.dto.request.UserRequest;
import org.example.final_server.dto.response.UserResponse;
import org.example.final_server.entity.user.User;

import java.util.Collection;

public interface UserFacade extends CrudFacade<UserRequest, UserResponse>{
    UserResponse getUserByEmail(String email);
    void attachCarVariantToUser(Long userId, Long carVariantId);
}
