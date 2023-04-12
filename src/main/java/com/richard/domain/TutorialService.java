package com.richard.domain;

import com.richard.infrastructure.persistence.entity.QTutorialEntity;
import com.richard.infrastructure.persistence.entity.TutorialEntity;
import com.richard.infrastructure.persistence.repositories.TutorialEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialService {

    private final TutorialEntityRepository tutorialEntityRepository;

    public TutorialService(TutorialEntityRepository tutorialEntityRepository) {
        this.tutorialEntityRepository = tutorialEntityRepository;
    }

    public TutorialEntity save(TutorialEntity tutorialEntity) {
        return tutorialEntityRepository.save(tutorialEntity);
    }

    public List<TutorialEntity> findByTitleContaining(String title) {
        return tutorialEntityRepository.findByTitleContaining(title);
    }

    public List<TutorialEntity> findByTitleContainingQueryDsl(String title) {
        List<TutorialEntity> entities = new ArrayList<>();
        tutorialEntityRepository.findAll(QTutorialEntity.tutorialEntity.title.eq(title)).forEach(entities::add);
        return  entities;
    }

    public List<TutorialEntity> findAll() {
        return tutorialEntityRepository.findAll();
    }

    public Optional<TutorialEntity> findById(String id) {
        return tutorialEntityRepository.findById(id);
    }

    public Page<TutorialEntity> findByPublished(boolean published, Pageable pageable) {
        return tutorialEntityRepository.findByPublished(published, pageable);
    }

    public Page<TutorialEntity> findByPublishedQueryDsl(boolean published, Pageable pageable) {
        return tutorialEntityRepository.findAll(QTutorialEntity.tutorialEntity.published.eq(published), pageable);
    }

    public void deleteById(String id) {
        tutorialEntityRepository.deleteById(id);
    }

    public Optional<TutorialEntity> updateTutorial(String id, TutorialEntity entity) {
        Optional<TutorialEntity> tutorialEntityOptional = tutorialEntityRepository.findById(id);

        tutorialEntityOptional.ifPresent(tutorialEntity -> {
            tutorialEntity.setTitle(entity.getTitle());
            tutorialEntity.setDescription(entity.getDescription());
            tutorialEntity.setPublished(entity.isPublished());
            tutorialEntityRepository.save(tutorialEntity);
        });

        return tutorialEntityOptional;
    }

}
