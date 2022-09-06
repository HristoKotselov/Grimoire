package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Campaign;
import com.dnd.Grimoire.model.Pc;
import com.dnd.Grimoire.model.Visibility;
import com.dnd.Grimoire.model.VisibilityType;
import com.dnd.Grimoire.service.PcService;
import com.dnd.Grimoire.service.VisibilityService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("api/visibility")
@AllArgsConstructor
public class VisibilityController {

    @NonNull
    private final VisibilityService visibilityService;

    @NonNull
    private final PcService pcService;

    @GetMapping("/{id}")
    public ResponseEntity<Visibility> getVisibilityById(@PathVariable("id") final Long visibilityId) {
        try {
            final Optional<Visibility> visibility = visibilityService.getVisibilityById(visibilityId);
            if (visibility.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(visibility.get());
            } else {
                log.error("Visibility with id {} was not found", visibilityId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving visibility with id {} failed because {}", visibilityId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{campaignId}")
    public ResponseEntity<Visibility> getDmVisibilityByCampaignId(@PathVariable("campaignId") final Long campaignId) {
        try {
            final Visibility visibility = visibilityService.getDmVisibilityByCampaignId(campaignId);
            if (visibility != null) {
                return ResponseEntity.status(HttpStatus.OK).body(visibility);
            } else {
                log.error("Dm visibility for campaign id {} was not found", campaignId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving Dm visibility for campaign id {} failed because {}", campaignId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/campaign/dm")
    public ResponseEntity<Visibility> getDmVisibilityByCampaign(@RequestBody final Campaign campaign) {
        try {
            final Visibility visibility = visibilityService.getDmVisibilityByCampaign(campaign);
            if (visibility != null) {
                return ResponseEntity.status(HttpStatus.OK).body(visibility);
            } else {
                log.error("Dm visibility for campaign {} was not found", campaign);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving Dm visibility for campaign {} failed because {}", campaign, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/campaign/party")
    public ResponseEntity<Visibility> getPartyVisibilityByCampaign(@RequestBody final Campaign campaign) {
        try {
            Pc dmPc = pcService.getPcByNameAndCampaign(campaign.getName(), campaign);
            final Visibility visibility = visibilityService.getVisibilityByOwnerAndType(dmPc, VisibilityType.PARTY);
            if (visibility != null) {
                return ResponseEntity.status(HttpStatus.OK).body(visibility);
            } else {
                log.error("Dm visibility for campaign {} was not found", campaign);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving Dm visibility for campaign {} failed because {}", campaign, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{pc}/{visibilityType}")
    public ResponseEntity<Visibility> getVisibilityByPcAndType(@PathVariable("pc") final Pc pc,
                                                               @PathVariable("visibilityType") final VisibilityType visibilityType) {
        try {
            final Visibility visibility = visibilityService.getVisibilityByOwnerAndType(pc, visibilityType);
            if (visibility != null) {
                return ResponseEntity.status(HttpStatus.OK).body(visibility);
            } else {
                log.error("Visibility for pc {} and type {} was not found", pc, visibilityType);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving visibility for pc {} and type {} failed because {}", pc, visibilityType, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
