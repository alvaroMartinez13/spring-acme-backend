package com.project.spring_acme_backend.Services.Likes;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.spring_acme_backend.Entities.Likes;
import com.project.spring_acme_backend.Entities.Posts;
import com.project.spring_acme_backend.Entities.Users;
import com.project.spring_acme_backend.Repositories.LikeRepository;
import com.project.spring_acme_backend.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    private final UserRepository userRepository;

    @Override
    public Likes addLike(Posts post, String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Optional<Likes> existingLike = likeRepository.findByUserAndPost(user, post);
        if (existingLike.isPresent()) {
            return null;
        }

        Likes like = Likes.builder()
                .user(user)
                .post(post)
                .build();

        return likeRepository.save(like);
    }

    @Override
    public Likes deleteLike(Posts post, String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Likes like = likeRepository.findByUserAndPost(user, post).orElse(null);

        if (like == null) {
            return null;
        }

        likeRepository.delete(like);
        return like;
    }

    @Override
    public int countLikesByPost(Posts post) {
        return likeRepository.countByPost(post);
    }

    public Users getUserByLike(String username, Posts post) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Likes like = likeRepository.findByUserAndPost(user, post).orElse(null);

        if (like == null) {
            return null;
        }

        return like.getUser();

    }

}
