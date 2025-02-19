package com.project.spring_acme_backend.Controllers.Posts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.spring_acme_backend.DTO.PostDTO;
import com.project.spring_acme_backend.Entities.Posts;
import com.project.spring_acme_backend.Services.Posts.PostServiceImpl;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @GetMapping("/following/{username}")
    public ResponseEntity<List<PostDTO>> getAllPosts(@PathVariable String username) {
        List<PostDTO> posts = postService.getAllPosts(username);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
        PostDTO post = postService.getPost(id);
        return post != null ? ResponseEntity.ok(post) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{username}")
    public ResponseEntity<String> addPost(@PathVariable String username, @RequestBody Posts post) {
        int result = postService.addPost(username, post);
        return result > 0 ? ResponseEntity.status(HttpStatus.CREATED).body("Post creado con éxito")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el post");
    }

    @PutMapping("/{username}/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @PathVariable String username,
            @RequestBody Posts post) {
        int result = postService.updatePost(id, username, post);
        return result > 0 ? ResponseEntity.ok("Post actualizado con éxito")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post no encontrado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        int result = postService.deletePost(id);
        return result > 0 ? ResponseEntity.ok("Post eliminado con éxito")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post no encontrado");
    }

    @GetMapping("/myPosts/{username}")
    public ResponseEntity<List<Posts>> getMyPosts(@PathVariable String username) {
        List<Posts> posts = postService.getMyPosts(username);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/following/desc/{username}")
    public ResponseEntity<List<Posts>> postsOrderByDesc(@PathVariable String username) {
        List<Posts> posts = postService.postsOrderByDesc(username);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/following/asc/{username}")
    public ResponseEntity<List<Posts>> postsOrderByAsc(@PathVariable String username) {
        List<Posts> posts = postService.postsOrderByAsc(username);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/following/relevant/{username}")
    public ResponseEntity<List<Posts>> postsOrderByRelevant(@PathVariable String username) {
        List<Posts> posts = postService.postsOrderByRelevant(username);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/{postId}/tags/{tagName}")
    public ResponseEntity<String> addTagToPost(@PathVariable Long postId, @PathVariable String tagName) {
        boolean success = postService.addTagToPost(postId, tagName);
        return success ? ResponseEntity.ok("Tag agregado correctamente")
                : ResponseEntity.badRequest().body("No se pudo agregar el tag");
    }

    @DeleteMapping("/{postId}/tags/{tagId}")
    public ResponseEntity<String> removeTagFromPost(@PathVariable Long postId, @PathVariable Long tagId) {
        boolean success = postService.removeTagFromPost(postId, tagId);
        return success ? ResponseEntity.ok("Tag eliminado correctamente")
                : ResponseEntity.badRequest().body("No se pudo eliminar el tag");
    }

}
