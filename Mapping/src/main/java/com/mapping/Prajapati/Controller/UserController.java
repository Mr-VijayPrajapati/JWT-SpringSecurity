package com.mapping.Prajapati.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.mapping.Prajapati.DTO.UserDTO;
import com.mapping.Prajapati.Service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api-user")
public class UserController {

    private final UserService userService;

    // Get all users
    @GetMapping("/Alluser")
    public List<UserDTO> getAllUser() {
        return userService.getAllUser();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    // Create new user
    @PostMapping("/New-user")
    public UserDTO createUser(@RequestBody UserDTO dto) {
        return userService.createUser(dto);
    }

    // Link student to user
    @PutMapping("/{userId}/link-student/{studentId}")
    public UserDTO linkStudentToUser(@PathVariable Long userId, @PathVariable Long studentId) {
        return userService.linkStudent(userId, studentId);
    }

    // Public endpoint
    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint. No JWT needed.";
    }
}
