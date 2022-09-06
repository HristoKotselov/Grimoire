package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.EntityDescription;
import com.dnd.Grimoire.service.EntityDescriptionService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("api/entity-description")
@AllArgsConstructor
public class EntityDescriptionController {

    @NonNull
    private final EntityDescriptionService entityDescriptionService;

    @GetMapping("/{id}")
    public ResponseEntity<EntityDescription> getEntityDescriptionById(@PathVariable("id") final Long entityDescriptionId) {
        try {
            final Optional<EntityDescription> entityDescription = entityDescriptionService.getEntityDescriptionById(entityDescriptionId);
            if (entityDescription.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(entityDescription.get());
            } else {
                log.error("Entity description with id {} was not found", entityDescriptionId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving entity description with id {} failed because {}", entityDescriptionId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<EntityDescription> createPcDescription(@RequestBody final EntityDescription entityDescription) {
        try {
            EntityDescription createdEntityDescription = entityDescriptionService.createEntityDescription(entityDescription);
            return ResponseEntity.status(HttpStatus.OK).body(createdEntityDescription);
        } catch (Exception e) {
            log.error("Adding entity description {} failed because {}", entityDescription, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<EntityDescription>> getAllPcDescriptions() {
        try {
            List<EntityDescription> allEntityDescriptions = entityDescriptionService.getAllEntityDescriptions();
            return ResponseEntity.status(HttpStatus.OK).body(allEntityDescriptions);
        } catch (Exception e) {
            log.error("Retrieving entity description list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
