package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Location;
import com.dnd.Grimoire.service.LocationService;
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
@RequestMapping("api/location")
@AllArgsConstructor
public class LocationController {

    @NonNull
    private final LocationService locationService;

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable("id") final Long locationId) {
        try {
            final Optional<Location> location = locationService.getLocationById(locationId);
            if (location.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(location.get());
            } else {
                log.error("Location with id {} was not found", locationId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving location with id {} failed because {}", locationId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Location> createLocation(@RequestBody final Location location) {
        try {
            Location createdLocation = locationService.createLocation(location);
            return ResponseEntity.status(HttpStatus.OK).body(createdLocation);
        } catch (Exception e) {
            log.error("Adding location {} failed because {}", location, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Location>> getAllLocations() {
        try {
            List<Location> allLocations = locationService.getAllLocations();
            return ResponseEntity.status(HttpStatus.OK).body(allLocations);
        } catch (Exception e) {
            log.error("Retrieving location list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}