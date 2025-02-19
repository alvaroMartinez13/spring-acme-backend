package com.project.spring_acme_backend.Services.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.spring_acme_backend.Config.User.Role;
import com.project.spring_acme_backend.Controllers.Auth.AuthResponse;
import com.project.spring_acme_backend.Controllers.Auth.LoginRequest;
import com.project.spring_acme_backend.Entities.Users;
import com.project.spring_acme_backend.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository repository;
        private final JwtService jwtService;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;

        public AuthResponse login(LoginRequest request) {
                authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                                                request.getPassword()));
                UserDetails user = repository.findByUsername(request.getUsername()).orElseThrow();
                String token = jwtService.getToken(user);
                return AuthResponse.builder()
                                .token(token)
                                .build();
        }

        public AuthResponse register(Users request) {
                Users user = Users.builder()
                                .name(request.getName())
                                .username(request.getUsername())
                                .celphone(request.getCelphone())
                                .email(request.getEmail())
                                .dateBirth(request.getDateBirth())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .profilePicture(profileImg())
                                .role(Role.USER)
                                .build();

                repository.save(user);

                return AuthResponse.builder()
                                .token(jwtService.getToken(user))
                                .build();
        }

        private String profileImg(){
                return "https://images.unsplash.com/photo-1511367461989-f85a21fda167?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cHJvZmlsZXxlbnwwfHwwfHx8MA%3D%3D";
        }

}
