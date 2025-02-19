package com.project.spring_acme_backend.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.project.spring_acme_backend.Entities.Comments;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String username;
    private CommentDTO comentarioPrincipal;
    private List<CommentDTO> respuestas;

    public CommentDTO(Comments comment) {
        this(comment, true);
    }

    private CommentDTO(Comments comment, boolean includeResponses) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.username = comment.getUser().getUsername();

        if (comment.getParentComment() != null) {
            this.comentarioPrincipal = new CommentDTO(comment.getParentComment(), false);
        }

        if (includeResponses) {
            this.respuestas = comment.getReplies().stream()
                    .map(c -> new CommentDTO(c, true)) 
                    .collect(Collectors.toList());
        }
    }
}
