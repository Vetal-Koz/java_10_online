package org.example.final_server.facade.impl;

import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.request.DataTableRequest;
import org.example.final_server.dto.request.UserRequest;
import org.example.final_server.dto.response.DataTableResponse;
import org.example.final_server.dto.response.UserResponse;
import org.example.final_server.entity.user.User;
import org.example.final_server.facade.UserFacade;
import org.example.final_server.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void create(UserRequest entity) {
    }

    @Override
    public void update(UserRequest entity, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserResponse findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<UserResponse> findAll(DataTableRequest request) {
        Page<User> page = userService.findAll(request);
        DataTableResponse<UserResponse> dataTableResponse = new DataTableResponse<>(page);
        dataTableResponse.setOrder(request.getOrder());
        dataTableResponse.setSort(request.getSort());
        List<UserResponse> userResponses = page.getContent()
                .stream()
                .map(UserResponse::new)
                .toList();
        dataTableResponse.setItems(userResponses);
        return dataTableResponse;
    }
}
