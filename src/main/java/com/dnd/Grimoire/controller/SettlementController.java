package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Settlement;
import com.dnd.Grimoire.service.SettlementService;
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
@RequestMapping("api/settlement")
@AllArgsConstructor
public class SettlementController {

    @NonNull
    private final SettlementService settlementService;

    @GetMapping("/{id}")
    public ResponseEntity<Settlement> getSettlementById(@PathVariable("id") final Long settlementId) {
        try {
            final Optional<Settlement> settlement = settlementService.getSettlementById(settlementId);
            if (settlement.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(settlement.get());
            } else {
                log.error("Settlement with id {} was not found", settlementId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving region with id {} failed because {}", settlementId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Settlement> createSettlement(@RequestBody final Settlement settlement) {
        try {
            Settlement createdSettlement = settlementService.createSettlement(settlement);
            return ResponseEntity.status(HttpStatus.OK).body(createdSettlement);
        } catch (Exception e) {
            log.error("Adding settlement {} failed because {}", settlement, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Settlement>> getAllSettlements() {
        try {
            List<Settlement> allSettlements = settlementService.getAllSettlements();
            return ResponseEntity.status(HttpStatus.OK).body(allSettlements);
        } catch (Exception e) {
            log.error("Retrieving settlement list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}