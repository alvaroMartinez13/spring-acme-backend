package com.project.spring_acme_backend.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.spring_acme_backend.Entities.Users;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Users a SET" +
            " a.name = COALESCE(:name, a.name)," +
            "a.email = COALESCE(:email, a.email)," +
            "a.bibliography = COALESCE(:bibliography, a.bibliography)," +
            "a.profilePicture = COALESCE(:profilePicture, a.profilePicture)" +
            "WHERE a.username = :username")
    int updateUser(String name, String username, String email, String bibliography, String profilePicture);

    @Query("SELECT u FROM Users u WHERE u.username <> :username " +
            "AND u.username NOT IN (SELECT f.followed.username FROM Follows f WHERE f.follower.username = :username)")
    List<Users> findUsersNotFollowedBy(@Param("username") String username);

    Optional<Users> findByUsername(String username);

    @Query("SELECT f.followed FROM Follows f WHERE f.follower.username = :username")
    List<Users> findFollowingByUsername(@Param("username") String username);

    List<Users> findByUsernameContainingIgnoreCaseOrNameContainingIgnoreCase(String username, String name);

}
