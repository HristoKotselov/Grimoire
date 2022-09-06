package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Continent;
import com.dnd.Grimoire.service.ContinentService;
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
@RequestMapping("api/continent")
@AllArgsConstructor
public class ContinentController {

    @NonNull
    private final ContinentService continentService;

    @GetMapping("/{id}")
    public ResponseEntity<Continent> getContinentById(@PathVariable("id") final Long continentId) {
        try {
            final Optional<Continent> continent = continentService.getContinentById(continentId);
            if (continent.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(continent.get());
            } else {
                log.error("Continent with id {} was not found", continentId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving continent with id {} failed because {}", continentId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Continent> createContinent(@RequestBody final Continent continent) {
        try {
            Continent createdContinent = continentService.createContinent(continent);
            return ResponseEntity.status(HttpStatus.OK).body(createdContinent);
        } catch (Exception e) {
            log.error("Adding continent {} failed because {}", continent, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Continent>> getAllContinents() {
        try {
            List<Continent> allContinents = continentService.getAllContinents();
            return ResponseEntity.status(HttpStatus.OK).body(allContinents);
        } catch (Exception e) {
            log.error("Retrieving continent list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}