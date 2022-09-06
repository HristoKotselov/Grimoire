package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Campaign;
import com.dnd.Grimoire.model.Pc;
import com.dnd.Grimoire.service.CampaignService;
import com.dnd.Grimoire.service.PcService;
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
@RequestMapping("api/pc")
@AllArgsConstructor
public class PcController {

    @NonNull
    private final PcService pcService;

    @NonNull
    private final CampaignService campaignService;

    @GetMapping("/{id}")
    public ResponseEntity<Pc> getPcById(@PathVariable("id") final Long pcId) {
        try {
            final Optional<Pc> pc = pcService.getPcById(pcId);
            if (pc.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(pc.get());
            } else {
                log.error("Pc with id {} was not found", pcId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving pc with id {} failed because {}", pcId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Pc> createPc(@RequestBody final Pc pc) {
        try {
//            Campaign campaign = campaignService.getCampaignByName(pc.getCampaign().getName());
            Pc createdPc = pcService.createPc(pc);
            if (createdPc == null) {
                log.error("Adding pc {} failed because account owner or campaign does not exist", pc);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pc);
            }
            return ResponseEntity.status(HttpStatus.OK).body(createdPc);
        } catch (Exception e) {
            log.error("Adding pc {} failed because {}", pc, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(pc);
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
