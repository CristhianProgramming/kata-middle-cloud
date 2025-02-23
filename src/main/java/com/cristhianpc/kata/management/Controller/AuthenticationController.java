package com.cristhianpc.kata.management.Controller;

import com.cristhianpc.kata.management.Models.Users;
import com.cristhianpc.kata.management.Services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final IUserService userService;

    public AuthenticationController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    ResponseEntity<?> singUp(
            @RequestBody Users userPayload
    ) {
        Users response = userService.createUser(userPayload);
        response.setId(0L);
        return ResponseEntity.status(201).body(response);
    }

}
