package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Plane;
import com.dnd.Grimoire.service.PlaneService;
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
@RequestMapping("api/plane")
@AllArgsConstructor
public class PlaneController {

    @NonNull
    private final PlaneService planeService;

    @GetMapping("/{id}")
    public ResponseEntity<Plane> getPlaneById(@PathVariable("id") final Long planeId) {
        try {
            final Optional<Plane> plane = planeService.getPlaneById(planeId);
            if (plane.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(plane.get());
            } else {
                log.error("Plane with id {} was not found", planeId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving plane with id {} failed because {}", planeId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Plane> createPlane(@RequestBody final Plane plane) {
        try {
            Plane createdPlane = planeService.createPlane(plane);
            return ResponseEntity.status(HttpStatus.OK).body(createdPlane);
        } catch (Exception e) {
            log.error("Adding plane {} failed because {}", plane, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Plane>> getAllPlanes() {
        try {
            List<Plane> allPlanes = planeService.getAllPlanes();
            return ResponseEntity.status(HttpStatus.OK).body(allPlanes);
        } catch (Exception e) {
            log.error("Retrieving plane list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}