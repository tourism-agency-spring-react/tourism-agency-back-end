package com.tourismagency.tourism_agency.presentation.controller;

import com.tourismagency.tourism_agency.presentation.dto.LoginRequestDTO;
import com.tourismagency.tourism_agency.presentation.dto.UserDTO;
import com.tourismagency.tourism_agency.presentation.payload.AuthResponse;
import com.tourismagency.tourism_agency.service.implementation.UserService;
import com.tourismagency.tourism_agency.service.interfaces.IAuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final IAuthService authService;

    public UserController(UserService userService, IAuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/{idUser}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long idUser) {
        return ResponseEntity.ok(userService.getUser(idUser));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid LoginRequestDTO user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequestDTO user) {
        return ResponseEntity.ok(authService.login(user));
    }

    @PutMapping("/update/{idUser}")
    public ResponseEntity<String> update(@PathVariable Long idUser, @RequestBody @Valid LoginRequestDTO user) {
        userService.updateUser(idUser, user);
        return ResponseEntity.ok("Usuario actualizado con exito.");
    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<String> delete(@PathVariable Long idUser) {
        userService.deleteUser(idUser);
        return ResponseEntity.ok("Usuario eliminado con exito.");
    }
}
