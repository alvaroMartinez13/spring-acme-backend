package com.project.spring_acme_backend.Repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.spring_acme_backend.Entities.Posts;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {

    List<Posts> findByUserUsername(String username);

    Collection<Posts> findAllByOrderByCreatedAtDesc();

    Collection<Posts> findAllByOrderByCreatedAtAsc();
}
