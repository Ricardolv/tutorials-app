package com.richard.infrastructure.persistence.repositories;

import com.richard.infrastructure.persistence.entity.TutorialEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface TutorialEntityRepository extends MongoRepository <TutorialEntity, String>, QuerydslPredicateExecutor<TutorialEntity> {
    List<TutorialEntity> findByTitleContaining(String title);
    Page<TutorialEntity> findByPublished(boolean published, Pageable pageable);
}
