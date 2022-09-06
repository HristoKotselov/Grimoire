package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.PcDescription;
import com.dnd.Grimoire.service.PcDescriptionService;
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
@RequestMapping("api/pc-description")
@AllArgsConstructor
public class PcDescriptionController {

    @NonNull
    private final PcDescriptionService pcDescriptionService;

    @GetMapping("/{id}")
    public ResponseEntity<PcDescription> getPcDescriptionById(@PathVariable("id") final Long pcDescriptionId) {
        try {
            final Optional<PcDescription> pcDescription = pcDescriptionService.getPcDescriptionById(pcDescriptionId);
            if (pcDescription.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(pcDescription.get());
            } else {
                log.error("Pc description with id {} was not found", pcDescriptionId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving pc description with id {} failed because {}", pcDescriptionId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<PcDescription> createPcDescription(@RequestBody final PcDescription pcDescription) {
        try {
            PcDescription createdPcDescription = pcDescriptionService.createPcDescription(pcDescription);
            return ResponseEntity.status(HttpStatus.OK).body(createdPcDescription);
        } catch (Exception e) {
            log.error("Adding pc description {} failed because {}", pcDescription, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<PcDescription>> getAllPcDescriptions() {
        try {
            List<PcDescription> allPcDescriptions = pcDescriptionService.getAllPcDescriptions();
            return ResponseEntity.status(HttpStatus.OK).body(allPcDescriptions);
        } catch (Exception e) {
            log.error("Retrieving pc description list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
