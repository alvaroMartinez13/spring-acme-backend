package com.project.spring_acme_backend.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.spring_acme_backend.Entities.Follows;
import com.project.spring_acme_backend.Entities.Users;

@Repository
public interface FollowRepository extends JpaRepository<Follows, Long> {

    // Buscar si un usuario sigue a otro
    Optional<Follows> findByFollowerAndFollowed(Users follower, Users followed);

    // Buscar la lista de seguidores de un usuario
    List<Follows> findByFollowed(Users followed);

    // Buscar la lista de seguidos por un usuario
    List<Follows> findByFollower(Users follower);
}
