package com.project.spring_acme_backend.Services.Tags;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.spring_acme_backend.Entities.Tags;
import com.project.spring_acme_backend.Repositories.TagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Tags addTag(String tag) {
        Optional<Tags> existingTag = tagRepository.findByTag(tag);
        if (existingTag.isPresent()) {
            throw new RuntimeException("El tag ya existe: " + tag);
        }

        Tags newTag = Tags.builder()
                .tag(tag)
                .build();

        return tagRepository.save(newTag);
    }

    @Override
    public boolean deleteTag(Long id) {
        return tagRepository.findById(id).map(tag -> {
            tagRepository.delete(tag);
            return true;
        }).orElse(false);
    }

    @Override
    public Tags getTag(Long id) {
        return tagRepository.findById(id).orElse(null); 
    }

}
