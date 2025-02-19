package com.project.spring_acme_backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.spring_acme_backend.Entities.Comments;
import com.project.spring_acme_backend.Entities.Posts;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByPostOrderByCreatedAtAsc(Posts post);
}
