package org.example.dao;

import org.example.entity.User;

public interface UserDao extends CrudDao<User> {

    Double getWholeBalanceById(Long id);
}
