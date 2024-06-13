package org.example.final_server.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.example.final_server.util.ExceptionUtil;
import org.example.final_server.util.ValidationUtil;

@Getter
@Setter
public class AuthRequest extends ApiRequest{

    @NotBlank(message = "email is not present")
    @Pattern(regexp = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
            + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$", message = "email is not valid")
    @Schema(description = "authentication field: email (unique username)")
    private String email;

    @NotBlank(message = "password is not present")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$\n", message = "password is not valid")
    @Schema(description = "authentication field: password")
    private String password;
}
