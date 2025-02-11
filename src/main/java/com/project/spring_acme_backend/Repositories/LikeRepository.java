package com.project.spring_acme_backend.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.spring_acme_backend.Entities.Like;
import com.project.spring_acme_backend.Entities.Post;
import com.project.spring_acme_backend.Entities.User;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndPost(User user, Post post);

    int countByPost(Post post);
}
