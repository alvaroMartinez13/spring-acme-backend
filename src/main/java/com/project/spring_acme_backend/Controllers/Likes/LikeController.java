package com.project.spring_acme_backend.Controllers.Likes;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.spring_acme_backend.Entities.Likes;
import com.project.spring_acme_backend.Entities.Posts;
import com.project.spring_acme_backend.Entities.Users;
import com.project.spring_acme_backend.Repositories.PostRepository;
import com.project.spring_acme_backend.Services.Likes.LikeServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeServiceImpl likeService;
    private final PostRepository postRepository;

    @PostMapping("/post/{postId}/user/{username}")
    public ResponseEntity<?> addLike(@PathVariable Long postId, @PathVariable String username) {
        Optional<Posts> post = postRepository.findById(postId);

        if (post.isEmpty()) {
            return ResponseEntity.badRequest().body("Publicación no encontrada");
        }

        Likes like = likeService.addLike(post.get(), username);
        if (like == null) {
            return ResponseEntity.ok(false); 
        }

        return ResponseEntity.ok(true); 
    }

    @DeleteMapping("/post/{postId}/user/{username}")
    public ResponseEntity<?> removeLike(@PathVariable Long postId, @PathVariable String username) {
        Optional<Posts> post = postRepository.findById(postId);

        if (post.isEmpty()) {
            return ResponseEntity.badRequest().body("Publicación no encontrada");
        }

        Likes like = likeService.deleteLike(post.get(), username);
        if (like == null) {
            return ResponseEntity.ok(false); 
        }

        return ResponseEntity.ok(true); 
    }

    @GetMapping("/count/{postId}")
    public ResponseEntity<?> countLikes(@PathVariable Long postId) {
        Optional<Posts> post = postRepository.findById(postId);

        if (post.isEmpty()) {
            return ResponseEntity.badRequest().body("Publicación no encontrada");
        }

        int likeCount = likeService.countLikesByPost(post.get());
        return ResponseEntity.ok(likeCount);
    }

    @GetMapping("/post/{postId}/user/{username}")
    public ResponseEntity<?> getUserByLike(@PathVariable Long postId, @PathVariable String username){
        Optional<Posts> post = postRepository.findById(postId);

        if (post.isEmpty()) {
            return ResponseEntity.badRequest().body("Publicación no encontrada");
        }

        Users user = likeService.getUserByLike(username, post.get());

        return ResponseEntity.ok(user != null);

    }

}
