package com.project.spring_acme_backend.Controllers.Follows;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.spring_acme_backend.DTO.FollowDTO;
import com.project.spring_acme_backend.Entities.Follows;
import com.project.spring_acme_backend.Entities.Users;
import com.project.spring_acme_backend.Services.Follows.FollowServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowServiceImpl followsService;

    @PostMapping("/{followerUsername}/follow/{followedUsername}")
    public ResponseEntity<?> followUser(
            @PathVariable String followerUsername,
            @PathVariable String followedUsername) {
        try {
            Boolean response = followsService.following(followerUsername, followedUsername);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al seguir al usuario.");
        }
    }

    @DeleteMapping("/{followerUsername}/unfollow/{followedUsername}")
    public ResponseEntity<String> unfollowUser(
            @PathVariable String followerUsername,
            @PathVariable String followedUsername) {
        try {
            String response = followsService.unFollowing(followerUsername, followedUsername);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al dejar de seguir al usuario.");
        }
    }

    @GetMapping("/{username}/followers")
    public ResponseEntity<List<Follows>> getFollowers(@PathVariable String username) {
        try {
            List<Follows> followers = followsService.getFollowers(username);
            return ResponseEntity.ok(followers);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{username}/following")
    public ResponseEntity<List<FollowDTO>> getFollowing(@PathVariable String username) {
        try {
            List<Users> following = followsService.getFollowing(username);

            List<FollowDTO> Following = following.stream()
            .map(user -> FollowDTO.builder()
            .idFollowUser(user.getId())
            .name(user.getName())
            .username(user.getUsername())
            .email(user.getEmail())
            .celphone(user.getCelphone())
            .dateBirth(user.getDateBirth())
            .bibliography(user.getBibliography())
            .profilePicture(user.getProfilePicture())
            .build()
            )
            .collect(Collectors.toList());

            return ResponseEntity.ok(Following);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{username}/not-followed")
    public ResponseEntity<List<FollowDTO>> getUsersNotFollowed(@PathVariable String username) {
        List<Users> usersNotFollowed = followsService.getUsersNotFollowedBy(username);
        List<FollowDTO> noFollowed = usersNotFollowed.stream()
                .map(user -> FollowDTO.builder()
                .idFollowUser(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .celphone(user.getCelphone())
                .dateBirth(user.getDateBirth())
                .bibliography(user.getBibliography())
                .profilePicture(user.getProfilePicture())
                .build()
                )
                .collect(Collectors.toList());
        return ResponseEntity.ok(noFollowed);
    }

}
