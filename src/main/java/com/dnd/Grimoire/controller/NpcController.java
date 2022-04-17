package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Npc;
import com.dnd.Grimoire.service.NpcService;
import com.dnd.Grimoire.service.PcService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("api/npc")
@AllArgsConstructor
public class NpcController {

    @NonNull
    private final NpcService npcService;

    @PostMapping("/create")
    public ResponseEntity<Npc> createPc(@RequestBody final Npc npc) {
        try {
            npcService.createNpc(npc);
            return ResponseEntity.status(HttpStatus.OK).body(npc);
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
