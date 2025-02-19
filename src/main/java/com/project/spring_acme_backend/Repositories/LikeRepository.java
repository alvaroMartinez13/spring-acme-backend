package com.project.spring_acme_backend.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.spring_acme_backend.Entities.Likes;
import com.project.spring_acme_backend.Entities.Posts;
import com.project.spring_acme_backend.Entities.Users;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUserAndPost(Users user, Posts post);

    int countByPost(Posts post);
}
