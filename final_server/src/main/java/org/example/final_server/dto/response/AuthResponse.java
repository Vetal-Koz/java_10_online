package org.example.final_server.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "authentication response")
@AllArgsConstructor
public class AuthResponse {

    @Schema(description = "authentication token")
    private String token;
}
