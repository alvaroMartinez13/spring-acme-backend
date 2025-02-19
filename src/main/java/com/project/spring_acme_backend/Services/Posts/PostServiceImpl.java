package com.project.spring_acme_backend.Services.Posts;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.spring_acme_backend.DTO.PostDTO;
import com.project.spring_acme_backend.Entities.Follows;
import com.project.spring_acme_backend.Entities.Posts;
import com.project.spring_acme_backend.Entities.Tags;
import com.project.spring_acme_backend.Entities.Users;
import com.project.spring_acme_backend.Repositories.PostRepository;
import com.project.spring_acme_backend.Repositories.TagRepository;
import com.project.spring_acme_backend.Repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    @Override
    public List<PostDTO> getAllPosts(String username) {
        Optional<Users> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return List.of();
        }

        Users user = userOptional.get();

        Set<Users> followedUsers = user.getFollowing().stream()
                .map(Follows::getFollowed)
                .collect(Collectors.toSet());

        return postRepository.findAll().stream()
                .filter(post -> followedUsers.contains(post.getUser()))
                .map(PostDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getPost(Long id) {
        Posts postFound = postRepository.findById(id).orElse(null);

        return new PostDTO(postFound);

    }

    @Override
    public int addPost(String username, Posts post) {
        Optional<Users> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            return 0;
        }

        Users user = userOpt.get();

        post.setUser(user);

        postRepository.save(post);
        return 1;
    }

    @Override
    public int updatePost(Long id, String username, Posts post) {

        Optional<Users> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty())
            return 0;

        Users user = userOpt.get();

        if (!postRepository.existsById(id))
            return 0;
        post.setId(id);
        post.setUser(user);
        postRepository.save(post);
        return 1;
    }

    @Override
    public int deletePost(Long id) {
        if (!postRepository.existsById(id))
            return 0;
        postRepository.deleteById(id);
        return 1;
    }

    @Override
    public List<Posts> getMyPosts(String username) {
        return postRepository.findByUserUsername(username);
    }

    @Override
    public List<Posts> postsOrderByDesc(String username) {
        Optional<Users> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return List.of();
        }

        Users user = userOpt.get();

        Set<Users> followedUsers = user.getFollowing().stream()
                .map(Follows::getFollowed)
                .collect(Collectors.toSet());

        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .filter(post -> followedUsers.contains(post.getUser()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Posts> postsOrderByAsc(String username) {
        Optional<Users> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return List.of();
        }

        Users user = userOpt.get();

        Set<Users> followedUsers = user.getFollowing().stream()
                .map(Follows::getFollowed)
                .collect(Collectors.toSet());

        return postRepository.findAllByOrderByCreatedAtAsc().stream()
                .filter(post -> followedUsers.contains(post.getUser()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Posts> postsOrderByRelevant(String username) {
        Optional<Users> userOptl = userRepository.findByUsername(username);
        if (userOptl.isEmpty()) {
            return List.of();
        }

        Users user = userOptl.get();

        return null;
    }

    @Override
    @Transactional
    public Boolean addTagToPost(Long postId, String tagName) {
        Optional<Posts> postOpt = postRepository.findById(postId);
        if (postOpt.isEmpty())
            return false;

        Posts post = postOpt.get();
        Tags tag = tagRepository.findByTag(tagName).orElseGet(() -> {
            Tags newTag = new Tags();
            newTag.setTag(tagName);
            return tagRepository.save(newTag);
        });

        if (!post.getTags().contains(tag)) {
            post.getTags().add(tag);
            postRepository.save(post);
            return true;
        }
        return false;

    }

    @Override
    @Transactional
    public Boolean removeTagFromPost(Long postId, Long tagId) {
        Optional<Posts> postOpt = postRepository.findById(postId);
        Optional<Tags> tagOpt = tagRepository.findById(tagId);

        if (postOpt.isEmpty() || tagOpt.isEmpty())
            return false;

        Posts post = postOpt.get();
        Tags tag = tagOpt.get();

        if (post.getTags().contains(tag)) {
            post.getTags().remove(tag);
            postRepository.save(post);
            return true;
        }
        return false;

    }

}
