package com.project.spring_acme_backend.Services.Users;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.spring_acme_backend.DTO.UserDTO;
import com.project.spring_acme_backend.Entities.Users;
import com.project.spring_acme_backend.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO findUser(String username) {
        Optional<Users> userFound = userRepository.findByUsername(username);

        Users user = userFound.orElseThrow(() -> 
        new UsernameNotFoundException("User " + username + " not found"));

        return UserDTO.builder()
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .celphone(user.getCelphone())
                .dateBirth(user.getDateBirth())
                .bibliography(user.getBibliography())
                .profilePicture(user.getProfilePicture())
                .build();
    }

    @Override
    public Boolean updateUserByProfile(UserDTO user) {
        int rowFounds = userRepository.updateUser(user.getName(), user.getUsername(),
                user.getEmail(), user.getBibliography(), user.getProfilePicture());

        return rowFounds > 0;
    }

}
