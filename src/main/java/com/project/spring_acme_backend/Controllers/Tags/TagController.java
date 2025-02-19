package com.project.spring_acme_backend.Controllers.Tags;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.spring_acme_backend.Entities.Tags;
import com.project.spring_acme_backend.Services.Tags.TagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<Tags> addTag(@RequestBody Tags tag) {
        try {
            Tags newTag = tagService.addTag(tag.getTag());
            return ResponseEntity.ok(newTag);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable Long id) {
        return tagService.deleteTag(id)
                ? ResponseEntity.ok("Tag eliminado con éxito")
                : ResponseEntity.status(404).body("Tag no encontrado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tags> getTag(@PathVariable Long id) {
        Tags tag = tagService.getTag(id);

        if (tag == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

}
