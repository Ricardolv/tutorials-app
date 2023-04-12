package com.richard.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "tutorials")
public class TutorialEntity {

    @Id
    private String id;
    private String title;
    private String description;
    private boolean published;

}
