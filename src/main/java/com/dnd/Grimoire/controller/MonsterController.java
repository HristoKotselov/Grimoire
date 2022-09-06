package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Monster;
import com.dnd.Grimoire.service.MonsterService;
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
@RequestMapping("api/monster")
@AllArgsConstructor
public class MonsterController {

    @NonNull
    private final MonsterService monsterService;

    @GetMapping("/{id}")
    public ResponseEntity<Monster> getMonsterById(@PathVariable("id") final Long monsterId) {
        try {
            final Optional<Monster> monster = monsterService.getMonsterById(monsterId);
            if (monster.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(monster.get());
            } else {
                log.error("Monster with id {} was not found", monsterId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving monster with id {} failed because {}", monsterId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Monster> createMonster(@RequestBody final Monster monster) {
        try {
            Monster createdMonster = monsterService.createMonster(monster);
            return ResponseEntity.status(HttpStatus.OK).body(createdMonster);
        } catch (Exception e) {
            log.error("Adding monster {} failed because {}", monster, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Monster>> getAllMonsters() {
        try {
            List<Monster> allMonsters = monsterService.getAllMonsters();
            return ResponseEntity.status(HttpStatus.OK).body(allMonsters);
        } catch (Exception e) {
            log.error("Retrieving monster list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
