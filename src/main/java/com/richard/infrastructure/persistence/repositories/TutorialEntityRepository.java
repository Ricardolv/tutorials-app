package com.richard.infrastructure.persistence.repositories;

import com.richard.infrastructure.persistence.entity.TutorialEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TutorialEntityRepository extends MongoRepository <TutorialEntity, String> {
    List<TutorialEntity> findByTitleContaining(String title);
    List<TutorialEntity> findByPublished(boolean published);
}
