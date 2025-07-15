package com.useradministration.user.controller;

import com.useradministration.user.entity.UserEntity;
import com.useradministration.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserEntity loginData) {
        String phone = loginData.getMobilePhone();
        String password = loginData.getPassword();

        UserEntity user = userService.loginOrCreate(phone, password);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/createuser")
    public ResponseEntity<Object> createUser(@RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/getusers")
    public ResponseEntity<List<UserEntity>> getUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        System.out.println("Buscando usuario con ID: " + id);
        Optional<UserEntity> userOpt = userService.getUserById(id);
        return userOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(
            @PathVariable Long id,
            @RequestBody UserEntity updatedUser) {

        UserEntity user = userService.updateUser(id, updatedUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
