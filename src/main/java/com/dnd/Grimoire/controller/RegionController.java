package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Region;
import com.dnd.Grimoire.service.RegionService;
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
@RequestMapping("api/region")
@AllArgsConstructor
public class RegionController {

    @NonNull
    private final RegionService regionService;

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable("id") final Long regionId) {
        try {
            final Optional<Region> region = regionService.getRegionById(regionId);
            if (region.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(region.get());
            } else {
                log.error("Region with id {} was not found", regionId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving region with id {} failed because {}", regionId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Region> createRegion(@RequestBody final Region region) {
        try {
            Region createdRegion = regionService.createRegion(region);
            return ResponseEntity.status(HttpStatus.OK).body(createdRegion);
        } catch (Exception e) {
            log.error("Adding region {} failed because {}", region, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Region>> getAllRegions() {
        try {
            List<Region> allRegions = regionService.getAllRegions();
            return ResponseEntity.status(HttpStatus.OK).body(allRegions);
        } catch (Exception e) {
            log.error("Retrieving region list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}