package com.cristhianpc.kata.management.Controller;

import com.cristhianpc.kata.management.Models.Users;
import com.cristhianpc.kata.management.Services.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    ResponseEntity<Page<Users>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int limit
    ) {
        Page<Users> response = userService.getAllUsers(PageRequest.of(page, limit));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{email}")
    ResponseEntity<Users> getuserByEmail(
            @PathVariable(name = "id") String email
    ) {
        Users response = userService.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    ResponseEntity<Users> updateUser(
            @RequestBody Users payload,
            @PathVariable Long id

    ) {
        Users response = userService.editUser(id, payload);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{id}")
    ResponseEntity<?> deleteUser(
            @PathVariable Long id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
