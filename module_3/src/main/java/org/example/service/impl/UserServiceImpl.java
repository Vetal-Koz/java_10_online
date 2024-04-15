package org.example.service.impl;

import org.example.config.ObjectFactory;
import org.example.dao.UserDao;
import org.example.entity.User;
import org.example.service.UserService;

import java.util.Collection;


public class UserServiceImpl implements UserService {
    UserDao userDao = ObjectFactory.getInstance().getService(UserDao.class);

    @Override
    public void create(User entity) {
        userDao.create(entity);
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    @Override
    public Collection<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public long count() {
        return userDao.count();
    }

    @Override
    public Double getWholeBalanceById(Long id) {
        return userDao.getWholeBalanceById(id);
    }
}
