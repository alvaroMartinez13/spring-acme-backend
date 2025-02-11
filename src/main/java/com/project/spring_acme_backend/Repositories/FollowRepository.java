package com.project.spring_acme_backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.spring_acme_backend.Entities.Follow;
import com.project.spring_acme_backend.Entities.User;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    
    boolean existsByFollowerAndFollowing(User follower, User following);

    void deleteByFollowerAndFollowing(User follower, User following);

    List<Follow> findByFollower(User follower);

}
