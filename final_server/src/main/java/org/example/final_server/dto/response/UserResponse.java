package org.example.final_server.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.example.final_server.entity.user.User;

import java.util.List;


@Getter
@Setter
public class UserResponse extends ApiResponse {
    private String email;
    private String role;
    private List<CarVariantResponse> carVariants;

    public UserResponse(User user) {
        setId(user.getId());
        this.email = user.getEmail();
        this.role = user.getRole().name();
        if (!CollectionUtils.isEmpty(user.getCarVariants())) {
            carVariants = user.getCarVariants().stream().map(CarVariantResponse::new).toList();
        }
    }
}
