package com.project.spring_acme_backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.spring_acme_backend.Entities.CommentMentionId;
import com.project.spring_acme_backend.Entities.CommentMentions;
import com.project.spring_acme_backend.Entities.Comments;

@Repository
public interface CommentMentionRepository  extends JpaRepository<CommentMentions, CommentMentionId> {
    void deleteByComment(Comments comment);
}
