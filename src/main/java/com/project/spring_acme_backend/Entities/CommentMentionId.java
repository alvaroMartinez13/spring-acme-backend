package com.project.spring_acme_backend.Entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class CommentMentionId implements Serializable {

    private Long commentId;
    private Long mentionedUserId;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getMentionedUserId() {
        return mentionedUserId;
    }

    public void setMentionedUserId(Long mentionedUserId) {
        this.mentionedUserId = mentionedUserId;
    }

}
