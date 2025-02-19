package com.project.spring_acme_backend.Services.Follows;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.spring_acme_backend.Entities.Follows;
import com.project.spring_acme_backend.Entities.Users;
import com.project.spring_acme_backend.Repositories.FollowRepository;
import com.project.spring_acme_backend.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followsRepository;
    private final UserRepository usersRepository;

    @Override
    public String unFollowing(String username, String following) {
        Optional<Users> followerFound = usersRepository.findByUsername(username);
        Optional<Users> followedFound = usersRepository.findByUsername(following);

        if (followerFound.isEmpty() || followedFound.isEmpty()) {
            return "Uno de los usuarios no existe.";
        }

        Users follower = followerFound.get();
        Users followed = followedFound.get();

        Optional<Follows> followOpt = followsRepository.findByFollowerAndFollowed(follower, followed);
        if (followOpt.isEmpty()) {
            return "No sigues a " + followed.getUsername();
        }

        followsRepository.delete(followOpt.get());

        return "Has dejado de seguir a " + followed.getUsername();

    }

    @Override
    public Boolean following(String username, String following) {
        Optional<Users> followerFound = usersRepository.findByUsername(username);
        Optional<Users> followedFound = usersRepository.findByUsername(following);

        if (followerFound.isEmpty() || followedFound.isEmpty()) {
            return false;
        }

        Users follower = followerFound.get();
        Users followed = followedFound.get();

        if (followsRepository.findByFollowerAndFollowed(follower, followed).isPresent()) {
            return false;
        }

        Follows follow = Follows.builder()
                .follower(follower)
                .followed(followed)
                .build();

        followsRepository.save(follow);

        return true;

    }

    @Override
    public List<Users> getFollowing(String username) {
        return usersRepository.findFollowingByUsername(username);
    }

    @Override
    public List<Follows> getFollowers(String username) {
        Optional<Users> userOpt = usersRepository.findByUsername(username);
        return userOpt.map(followsRepository::findByFollowed).orElse(null);
    }

    public List<Users> getUsersNotFollowedBy(String username) {
        return usersRepository.findUsersNotFollowedBy(username);
    }

}
