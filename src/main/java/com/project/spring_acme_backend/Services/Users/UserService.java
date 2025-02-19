package com.project.spring_acme_backend.Services.Users;

import java.util.List;

import com.project.spring_acme_backend.DTO.UserDTO;
import com.project.spring_acme_backend.Entities.Users;

public interface UserService {

    List<Users> findAllUsers();

    UserDTO findUser(String username);

    Boolean updateUserByProfile(UserDTO user);

}
