package org.example.final_server.service;

import org.example.final_server.entity.user.User;

public interface UserService extends CrudService<User>{

    User findUserByEmail(String email);

    void attachCarVariantToUser(Long userId, Long carVariantId);
}
