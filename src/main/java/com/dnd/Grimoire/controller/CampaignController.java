package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Campaign;
import com.dnd.Grimoire.service.CampaignService;
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
@RequestMapping("api/campaign")
@AllArgsConstructor
public class CampaignController {

    @NonNull
    private final CampaignService campaignService;

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable("id") final Long campaignId) {
        try {
            final Optional<Campaign> campaign = campaignService.getCampaignById(campaignId);
            if (campaign.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(campaign.get());
            } else {
                log.error("Campaign with id {} was not found", campaignId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving campaign with id {} failed because {}", campaignId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Campaign> createCampaign(@RequestBody final Campaign campaign) {
        try {
            Campaign createdCampaign = campaignService.createCampaign(campaign);
            return ResponseEntity.status(HttpStatus.OK).body(createdCampaign);
        } catch (Exception e) {
            log.error("Adding campaign {} failed because {}", campaign, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Campaign>> getAllCampaigns() {
        try {
            List<Campaign> allCampaigns = campaignService.getAllCampaigns();
            return ResponseEntity.status(HttpStatus.OK).body(allCampaigns);
        } catch (Exception e) {
            log.error("Retrieving campaign list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
