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

@Log4j2
@RestController
@RequestMapping("api/campaign")
@AllArgsConstructor
public class CampaignController {

    @NonNull
    private final CampaignService campaignService;

    @PostMapping("/create")
    public ResponseEntity<Campaign> createCampaign(@RequestBody final Campaign campaign) {
        try {
            campaignService.createCampaign(campaign);
            return ResponseEntity.status(HttpStatus.OK).body(campaign);
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
