package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Place;
import com.dnd.Grimoire.service.PlaceService;
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
@RequestMapping("api/place")
@AllArgsConstructor
public class PlaceController {

    @NonNull
    private final PlaceService placeService;

    @GetMapping("/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable("id") final Long placeId) {
        try {
            final Optional<Place> place = placeService.getPlaceById(placeId);
            if (place.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(place.get());
            } else {
                log.error("Place with id {} was not found", placeId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving place with id {} failed because {}", placeId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Place> createPlace(@RequestBody final Place place) {
        try {
            Place createdPlace = placeService.createPlace(place);
            return ResponseEntity.status(HttpStatus.OK).body(createdPlace);
        } catch (Exception e) {
            log.error("Adding place {} failed because {}", place, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Place>> getAllPlaces() {
        try {
            List<Place> allPlaces = placeService.getAllPlaces();
            return ResponseEntity.status(HttpStatus.OK).body(allPlaces);
        } catch (Exception e) {
            log.error("Retrieving place list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}