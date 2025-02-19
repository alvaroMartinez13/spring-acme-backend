package com.project.spring_acme_backend.DTO;

import java.util.Date;

import com.project.spring_acme_backend.Entities.Likes;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LikesDTO {

    private String username;
    private String celphone;
    private String email;
    private Date dateBirth;
    private String bibliography;
    private String profilePicture;

    public LikesDTO(Likes like) {
        this.username = like.getUser().getUsername();
        this.celphone = like.getUser().getCelphone();
        this.email = like.getUser().getEmail();
        this.dateBirth = like.getUser().getDateBirth();
        this.bibliography = like.getUser().getBibliography();
        this.profilePicture = like.getUser().getProfilePicture();
    }

}
