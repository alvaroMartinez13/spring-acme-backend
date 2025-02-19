package com.project.spring_acme_backend.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.project.spring_acme_backend.Entities.Posts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {

    private Long id;
    private String imageUrl;
    private String description;
    private LocalDateTime datePost;
    private String username;
    private String imageProfile;
    private List<String> tags;
    private List<LikesDTO> likes;
    private List<CommentDTO> comments;

    public PostDTO(Posts post) {
        this.id = post.getId();
        this.imageUrl = post.getImageUrl();
        this.description = post.getDescription();
        this.datePost = getTimeByPost(post);
        this.username = post.getUser().getUsername();
        this.imageProfile = post.getUser().getProfilePicture();
        this.tags = getTags(post);
        this.likes = getLikes(post);
        this.comments = getComments(post);
    }

    private List<String> getTags(Posts post) {
        return Optional.ofNullable(post.getTags())
                .orElse(List.of())
                .stream()
                .map(tag -> tag.getTag())
                .collect(Collectors.toList());
    }

    private List<LikesDTO> getLikes(Posts post) {
        return Optional.ofNullable(post.getLikes())
                .orElse(List.of())
                .stream()
                .map(LikesDTO::new)
                .collect(Collectors.toList());
    }

    private List<CommentDTO> getComments(Posts post) {
        return post.getComments().stream()
                .filter(comment -> comment.getParentComment() == null)
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }

    private LocalDateTime getTimeByPost(Posts post){

        return post.getUpdatedAt() != null ? post.getUpdatedAt() : post.getCreatedAt();

    }


}
