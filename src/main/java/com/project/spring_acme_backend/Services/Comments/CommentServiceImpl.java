package com.project.spring_acme_backend.Services.Comments;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.project.spring_acme_backend.DTO.CommentDTO;
import com.project.spring_acme_backend.Entities.CommentMentions;
import com.project.spring_acme_backend.Entities.Comments;
import com.project.spring_acme_backend.Entities.Posts;
import com.project.spring_acme_backend.Entities.Users;
import com.project.spring_acme_backend.Repositories.CommentMentionRepository;
import com.project.spring_acme_backend.Repositories.CommentRepository;
import com.project.spring_acme_backend.Repositories.PostRepository;
import com.project.spring_acme_backend.Repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentMentionRepository commentMentionRepository;

    public void processMentions(Comments comment, String content) {
        Pattern pattern = Pattern.compile("@(\\w+)");
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            String mentionedUsername = matcher.group(1);
            userRepository.findByUsername(mentionedUsername).ifPresent(user -> {
                CommentMentions mention = new CommentMentions();
                mention.setComment(comment);
                mention.setMentionedUser(user);
                commentMentionRepository.save(mention);
            });
        }
    }

    @Override
    public List<Comments> getCommentsByPost(Long postId) {
        Optional<Posts> post = postRepository.findById(postId);
        return post.map(commentRepository::findByPostOrderByCreatedAtAsc).orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public Comments addComment(Posts post, String username, String content) {
        Users user = userRepository.findByUsername(username).orElseThrow();
        Comments comment = commentRepository.save(Comments.builder().user(user).post(post).content(content).build());

        processMentions(comment, content);

        return comment;
    }

    @Override
    public CommentDTO getComment(Long id) {
        Optional<Comments> commentOpt = commentRepository.findById(id);
        if (commentOpt.isEmpty()) {
            return null;
        }

        Comments comment = commentOpt.get();

        return new CommentDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long id, String content) {
        Optional<Comments> commentOpt = commentRepository.findById(id);

        if (commentOpt.isEmpty()) {
            return null;
        }

        Comments comment = commentOpt.get();

        commentMentionRepository.deleteByComment(comment);

        comment.setContent(content);
        commentRepository.save(comment);

        processMentions(comment, content);

        return new CommentDTO(comment);
    }

    @Override
    public Boolean deleteComment(Long id) {
        Optional<Comments> commentOpt = commentRepository.findById(id);
        if (commentOpt.isPresent()) {
            Comments comment = commentOpt.get();

            commentMentionRepository.deleteByComment(comment);

            commentRepository.delete(comment);

            return true;
        }
        return false;
    }

    public Comments addCommentByComment(Long parentCommentId, String usernameComment, String content) {
        Optional<Comments> commentParent = commentRepository.findById(parentCommentId);
        Optional<Users> userFound = userRepository.findByUsername(usernameComment);

        if (userFound.isEmpty() || commentParent.isEmpty())
            return null;

        Comments parent = commentParent.get();

        Comments newComment = addComment(parent.getPost(), usernameComment, content);
        newComment.setParentComment(parent);

        return commentRepository.save(newComment);
    }

    @Override
    public int countCommentsByPost(Posts post) {
        return commentRepository.findByPostOrderByCreatedAtAsc(post).size();
    }

}
