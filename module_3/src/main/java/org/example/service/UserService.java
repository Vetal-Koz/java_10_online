package org.example.service;

import org.example.entity.User;

public interface UserService extends CrudService<User> {
    Double getWholeBalanceById(Long id);
}
