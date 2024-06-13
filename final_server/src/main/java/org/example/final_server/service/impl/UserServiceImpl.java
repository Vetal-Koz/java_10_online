package org.example.final_server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.request.DataTableRequest;
import org.example.final_server.entity.user.User;
import org.example.final_server.exception.EntityNotFoundException;
import org.example.final_server.exception.NotValidDataException;
import org.example.final_server.repository.user.UserRepository;
import org.example.final_server.service.UserService;
import org.example.final_server.util.ExceptionUtil;
import org.example.final_server.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public void create(User entity) {
        checkCorrectUser(entity);
        userRepository.save(entity);
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND.getMessage()));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<User> findAll(DataTableRequest request) {
        return null;
    }

    private void checkCorrectUser(User entity){
        checkIdIsNoNull(entity.getId());
        checkEmailIsNoNull(entity.getEmail());
        checkEmailIsValid(entity.getEmail());
        checkEmailIsNotExist(entity.getEmail());
        checkPasswordIsNoNull(entity.getPassword());
    }

    private void checkIdIsNoNull(Long id){
        if (id != null){
            throw new NotValidDataException(ExceptionUtil.USER_ALREADY_EXIST.getMessage());
        }
    }

    private void checkEmailIsNoNull(String email){
        if (email == null){
            throw new NotValidDataException(ExceptionUtil.EMAIL_IS_NOT_PRESENT.getMessage());
        }
    }

    private void checkEmailIsValid(String email){
        if (!email.matches(ValidationUtil.EMAIL_REGEX_PATTERN.getText())){
            throw new NotValidDataException(ExceptionUtil.EMAIL_IS_NOT_VALID.getMessage());
        }
    }

    private void checkEmailIsNotExist(String email){
        if (userRepository.existsByEmail(email)){
            throw new NotValidDataException(ExceptionUtil.EMAIL_IS_EXIST.getMessage());
        }
    }

    private void checkPasswordIsNoNull(String password){
        if (password == null){
            throw new NotValidDataException(ExceptionUtil.PASSWORD_IS_NOT_PRESENT.getMessage());
        }
    }




}
