package com.project.spring_acme_backend.Controllers.Users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.spring_acme_backend.DTO.UserDTO;
import com.project.spring_acme_backend.Entities.Users;
import com.project.spring_acme_backend.Repositories.UserRepository;
import com.project.spring_acme_backend.Services.Users.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Users>> getUsers() {
        List<Users> users = userService.findAllUsers();

        return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username) {
        try {
            UserDTO user = userService.findUser(username);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody UserDTO user) {
        try {
            userService.findUser(username);
            boolean isUpdated = userService.updateUserByProfile(user);

            if (!isUpdated) {
                return ResponseEntity.badRequest().body("No se pudo actualizar el usuario.");
            }

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<UserDTO> searchUsers(@RequestParam String query) {
        
        List<Users> users = userRepository.findByUsernameContainingIgnoreCaseOrNameContainingIgnoreCase(query, query);

        return users.stream().map(user -> UserDTO.builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .profilePicture(user.getProfilePicture())
                .build())
                .collect(Collectors.toList());
    }

}
