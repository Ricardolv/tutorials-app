package com.richard.domain;

import com.richard.infrastructure.persistence.entity.TutorialEntity;
import com.richard.infrastructure.persistence.repositories.TutorialEntityRepository;
import org.springframework.stereotype.Service;

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

    public List<TutorialEntity> findAll() {
        return tutorialEntityRepository.findAll();
    }

    public Optional<TutorialEntity> findById(String id) {
        return tutorialEntityRepository.findById(id);
    }

    public List<TutorialEntity> findByPublished(boolean published) {
        return tutorialEntityRepository.findByPublished(true);
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
