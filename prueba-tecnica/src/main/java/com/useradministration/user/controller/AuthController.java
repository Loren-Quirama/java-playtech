
package com.useradministration.user.controller;

import com.useradministration.security.JwtUtil;
import com.useradministration.user.dto.LoginRequest;
import com.useradministration.user.entity.UserEntity;
import com.useradministration.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Optional<UserEntity> userOpt = userRepository.findByMobilePhone(request.getMobilePhone());
        System.out.println("Buscando usuario con móvil: " + userOpt.isPresent());
        System.out.println("telefono " + request.getMobilePhone());

        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();

            if (user.getPassword().equals(request.getPassword())) {
                String token = jwtUtil.generateToken(user.getMobilePhone());

                return ResponseEntity.ok(Map.of(
                        "token", token,
                        "user", user
                ));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
    }
}
