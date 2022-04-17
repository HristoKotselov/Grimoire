package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Pc;
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
@RequestMapping("api/pc")
@AllArgsConstructor
public class PcController {

    @NonNull
    private final PcService pcService;

    @PostMapping("/create")
    public ResponseEntity<Pc> createPc(@RequestBody final Pc pc) {
        try {
            pcService.createPc(pc);
            return ResponseEntity.status(HttpStatus.OK).body(pc);
        } catch (Exception e) {
            log.error("Adding pc {} failed because {}", pc, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Pc>> getAllPc() {
        try {
            List<Pc> allPcs = pcService.getAllPcs();
            return ResponseEntity.status(HttpStatus.OK).body(allPcs);
        } catch (Exception e) {
            log.error("Retrieving pc list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
