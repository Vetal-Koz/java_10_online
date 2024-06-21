package org.example.final_server.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest extends ApiRequest {
    @NotBlank(message = "email is not present")
    private String email;
}
