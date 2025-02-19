package com.project.spring_acme_backend.Controllers.Comments;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.spring_acme_backend.DTO.CommentDTO;
import com.project.spring_acme_backend.Entities.Comments;
import com.project.spring_acme_backend.Entities.Posts;
import com.project.spring_acme_backend.Repositories.PostRepository;
import com.project.spring_acme_backend.Services.Comments.CommentServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;
    private final PostRepository postRepository;

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(@PathVariable Long postId) {
        List<Comments> commentsFounds = commentService.getCommentsByPost(postId);

        if (commentsFounds.isEmpty())
            ResponseEntity.noContent().build();

        List<CommentDTO> comments = commentsFounds.stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(comments);
    }

    @PostMapping("/post/{postId}/user/{username}")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long postId, @PathVariable String username,
            @RequestParam String content) {
        Posts post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            return ResponseEntity.badRequest().build();
        }

        Comments comment = commentService.addComment(post, username, content);
        return ResponseEntity.ok(new CommentDTO(comment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable Long id) {
        CommentDTO comment = commentService.getComment(id);
        if (comment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestParam String content) {
        CommentDTO updated = commentService.updateComment(id, content);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long id) {
        Boolean deleted = commentService.deleteComment(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{parentCommentId}/reply")
    public ResponseEntity<CommentDTO> addCommentByComment(@PathVariable("parentCommentId") Long parentCommentId,
            @RequestParam("username") String username,
            @RequestParam("content") String content) {
        Comments reply = commentService.addCommentByComment(parentCommentId, username, content);
        return reply != null ? ResponseEntity.ok(new CommentDTO(reply)) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/post/{postId}/count")
    public ResponseEntity<Integer> countCommentsByPost(@PathVariable Long postId) {
        return postRepository.findById(postId)
                .map(post -> ResponseEntity.ok(commentService.countCommentsByPost(post)))
                .orElse(ResponseEntity.notFound().build());
    }

}
