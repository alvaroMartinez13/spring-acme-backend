package com.project.spring_acme_backend.Services.Comments;

import java.util.List;

import com.project.spring_acme_backend.DTO.CommentDTO;
import com.project.spring_acme_backend.Entities.Comments;
import com.project.spring_acme_backend.Entities.Posts;

public interface CommentService {

    List<Comments> getCommentsByPost(Long postId);
    Comments addComment(Posts post, String username, String content);
    CommentDTO getComment(Long id);
    CommentDTO updateComment(Long id, String content);
    Boolean deleteComment(Long id);
    Comments addCommentByComment(Long parentCommentId, String usernameComment, String content);
    int countCommentsByPost(Posts post);

}
