package com.richard.infrastructure.resources.converter;

import com.richard.infrastructure.persistence.entity.TutorialEntity;
import com.richard.infrastructure.resources.request.TutorialRequest;
import com.richard.infrastructure.resources.response.TutorialResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.richard.infrastructure.resources.converter.mapper.TutorialMapper.INSTANCE;

@Component
public class TutorialConverter {

    public TutorialEntity toEntity(TutorialRequest request) {
        return INSTANCE.toEntity(request);
    }

    public TutorialResponse toResponse(TutorialEntity tutorialEntity) {
        return INSTANCE.toResponse(tutorialEntity);
    }

    public List<TutorialResponse> toCollectionResponse(List<TutorialEntity> tutorialEntities) {
        return tutorialEntities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Page<TutorialResponse> toCollectionResponsePage(Page<TutorialEntity> tutorials) {
        return tutorials.map(this::toResponse);
    }
}
