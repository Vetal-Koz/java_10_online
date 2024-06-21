package org.example.final_server.controller;


import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.response.ResponseContainer;
import org.example.final_server.dto.response.UserResponse;
import org.example.final_server.facade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/{email}")
    public ResponseEntity<ResponseContainer<UserResponse>> findCurrentUser(@PathVariable("email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body((new ResponseContainer<>(userFacade.getUserByEmail(email))));
    }

    @PostMapping("/{userId}/attached/{carVariantId}")
    public ResponseEntity<ResponseContainer<Boolean>> addCarVariantToUser(
            @PathVariable("userId") Long userId,
            @PathVariable("carVariantId") Long carVariantId) {
        userFacade.attachCarVariantToUser(userId, carVariantId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseContainer<>(true));
    }


}
