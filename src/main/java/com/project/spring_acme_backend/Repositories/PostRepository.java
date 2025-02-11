package com.project.spring_acme_backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.spring_acme_backend.Entities.Post;
import com.project.spring_acme_backend.Entities.User;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserOrderByCreatedAtDesc(User user);

    List<Post> findByUserInOrderByCreatedAtDesc(List<User> users);
}
