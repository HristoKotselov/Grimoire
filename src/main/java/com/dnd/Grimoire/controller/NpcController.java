package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Npc;
import com.dnd.Grimoire.service.NpcService;
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
@RequestMapping("api/npc")
@AllArgsConstructor
public class NpcController {

    @NonNull
    private final NpcService npcService;

    @GetMapping("/{id}")
    public ResponseEntity<Npc> getNpcById(@PathVariable("id") final Long npcId) {
        try {
            final Optional<Npc> npc = npcService.getNpcById(npcId);
            if (npc.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(npc.get());
            } else {
                log.error("Npc with id {} was not found", npcId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving npc with id {} failed because {}", npcId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Npc> createNpc(@RequestBody final Npc npc) {
        try {
            Npc createdNpc = npcService.createNpc(npc);
            return ResponseEntity.status(HttpStatus.OK).body(createdNpc);
        } catch (Exception e) {
            log.error("Adding npc {} failed because {}", npc, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Npc>> getAllNpcs() {
        try {
            List<Npc> allPcs = npcService.getAllNpcs();
            return ResponseEntity.status(HttpStatus.OK).body(allPcs);
        } catch (Exception e) {
            log.error("Retrieving npc list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
