package com.project.spring_acme_backend.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.spring_acme_backend.Entities.Tags;

@Repository
public interface TagRepository extends JpaRepository<Tags, Long> {

    Optional<Tags> findByTag(String tag);

}
