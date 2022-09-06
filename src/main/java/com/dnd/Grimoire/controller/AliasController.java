package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Alias;
import com.dnd.Grimoire.service.AliasService;
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
@RequestMapping("api/alias")
@AllArgsConstructor
public class AliasController {

    @NonNull
    private final AliasService aliasService;

    @GetMapping("/{id}")
    public ResponseEntity<Alias> getAliasById(@PathVariable("id") final Long aliasId) {
        try {
            final Optional<Alias> alias = aliasService.getAliasById(aliasId);
            if (alias.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(alias.get());
            } else {
                log.error("Alias with id {} was not found", aliasId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving alias with id {} failed because {}", aliasId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Alias> createAlias(@RequestBody final Alias alias) {
        try {
            Alias createdAlias = aliasService.createAlias(alias);
            return ResponseEntity.status(HttpStatus.OK).body(createdAlias);
        } catch (Exception e) {
            log.error("Adding alias {} failed because {}", alias, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Alias>> getAllAliases() {
        try {
            List<Alias> allItems = aliasService.getAllAliases();
            return ResponseEntity.status(HttpStatus.OK).body(allItems);
        } catch (Exception e) {
            log.error("Retrieving alias list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}