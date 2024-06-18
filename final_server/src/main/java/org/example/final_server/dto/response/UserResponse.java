package org.example.final_server.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.example.final_server.entity.car.CarVariant;
import org.example.final_server.entity.user.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
public class UserResponse extends ApiResponse {
    private String email;
    private List<CarVariantResponse> carVariants;

    public UserResponse(User user){
        setId(user.getId());
        this.email = user.getEmail();
        if(!CollectionUtils.isEmpty(user.getCarVariants())){
            carVariants = user.getCarVariants().stream().map(CarVariantResponse::new).toList();
        }
    }
}
