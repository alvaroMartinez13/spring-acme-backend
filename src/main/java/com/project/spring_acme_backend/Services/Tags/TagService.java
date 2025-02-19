package com.project.spring_acme_backend.Services.Tags;

import com.project.spring_acme_backend.Entities.Tags;

public interface TagService {
    
    Tags addTag(String tag);
    boolean deleteTag(Long id);
    Tags getTag(Long id);

}
