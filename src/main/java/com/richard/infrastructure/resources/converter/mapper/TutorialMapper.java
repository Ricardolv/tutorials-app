package com.richard.infrastructure.resources.converter.mapper;

import com.richard.infrastructure.persistence.entity.TutorialEntity;
import com.richard.infrastructure.resources.request.TutorialRequest;
import com.richard.infrastructure.resources.response.TutorialResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class TutorialMapper {
    public static final TutorialMapper INSTANCE = Mappers.getMapper(TutorialMapper.class);
    public abstract TutorialResponse toResponse(TutorialEntity tutorialEntity);
    public abstract TutorialEntity toEntity(TutorialRequest request);
}
