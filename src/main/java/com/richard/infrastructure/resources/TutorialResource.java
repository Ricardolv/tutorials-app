package com.richard.infrastructure.resources;

import com.richard.domain.TutorialService;
import com.richard.infrastructure.persistence.entity.TutorialEntity;
import com.richard.infrastructure.resources.converter.TutorialConverter;
import com.richard.infrastructure.resources.request.TutorialRequest;
import com.richard.infrastructure.resources.response.TutorialResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tutorials")
public class TutorialResource {

    private final TutorialService tutorialService;
    private final TutorialConverter tutorialConverter;

    @PostMapping
    public ResponseEntity<HttpStatus> createTutorial(@RequestBody TutorialRequest request, HttpServletResponse response) {
        TutorialEntity tutorialEntity = tutorialService.save(tutorialConverter.toEntity(request));
        fromCurrentRequestUri(response, tutorialEntity.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<TutorialResponse>> getAllTutorials(@RequestParam(required = false) String title) {
        List<TutorialResponse> tutorials;

        if (hasText(title)) {
            final var byTitleContaining = tutorialService.findByTitleContaining(title);
            tutorials = tutorialConverter.toCollectionResponse(byTitleContaining);
        } else {
            final var findAll = tutorialService.findAll();
            tutorials = tutorialConverter.toCollectionResponse(findAll);
        }

        if (tutorials.isEmpty())
            return ResponseEntity.status(NO_CONTENT).build();
        else
            return ResponseEntity.ok(tutorials);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorialResponse> getTutorialById(@PathVariable("id") String id) {
        Optional<TutorialEntity> tutorialEntityOptional = tutorialService.findById(id);

        return tutorialEntityOptional.map(tutorialEntity ->
                ResponseEntity.ok(tutorialConverter.toResponse(tutorialEntity)))
                .orElseGet(() -> ResponseEntity.status(NO_CONTENT).build());
    }

    @GetMapping("/published")
    public ResponseEntity<List<TutorialResponse>> findByPublished() {
        List<TutorialEntity> tutorials = tutorialService.findByPublished(true);

        if (tutorials.isEmpty()) {
            return ResponseEntity.status(NO_CONTENT).build();
        }
        return ResponseEntity.ok(tutorialConverter.toCollectionResponse(tutorials));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutorialResponse> updateTutorial(@PathVariable("id") String id, @RequestBody TutorialRequest request) {
        Optional<TutorialEntity> tutorialEntityOptional = tutorialService.updateTutorial(id, tutorialConverter.toEntity(request));

        return tutorialEntityOptional.map(tutorialEntity ->
                        ResponseEntity.ok(tutorialConverter.toResponse(tutorialEntity)))
                .orElseGet(() -> ResponseEntity.status(NO_CONTENT).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
        tutorialService.deleteById(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    private void fromCurrentRequestUri(HttpServletResponse response, String code) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}")
                .buildAndExpand(code).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
