package com.project.spring_acme_backend.Entities;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Comment_Mentions")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentMentions {

    @EmbeddedId
    private CommentMentionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("commentId")
    @JoinColumn(name = "comment_id", nullable = false)
    private Comments comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("mentionedUserId")
    @JoinColumn(name = "mentioned_user_id", nullable = false)
    private Users mentionedUser;

    public CommentMentionId getId() {
        return id;
    }

    public void setId(CommentMentionId id) {
        this.id = id;
    }

    public Comments getComment() {
        return comment;
    }

    public void setComment(Comments comment) {
        this.comment = comment;
    }

    public Users getMentionedUser() {
        return mentionedUser;
    }

    public void setMentionedUser(Users mentionedUser) {
        this.mentionedUser = mentionedUser;
    }

}
