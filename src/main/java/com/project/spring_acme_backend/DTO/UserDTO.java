package com.project.spring_acme_backend.DTO;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDTO {
    String name;
    String username;
    String email;
    String celphone;
    Date dateBirth;
    String bibliography;
    String profilePicture;
}
