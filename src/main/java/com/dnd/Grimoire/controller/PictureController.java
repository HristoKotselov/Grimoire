package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Picture;
import com.dnd.Grimoire.service.PictureService;
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
@RequestMapping("api/picture")
@AllArgsConstructor
public class PictureController {

    @NonNull
    private final PictureService pictureService;

    @GetMapping("/{id}")
    public ResponseEntity<Picture> getPlaceById(@PathVariable("id") final Long pictureId) {
        try {
            final Optional<Picture> picture = pictureService.getPictureById(pictureId);
            if (picture.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(picture.get());
            } else {
                log.error("Picture with id {} was not found", pictureId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving picture with id {} failed because {}", pictureId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Picture> createPicture(@RequestBody final Picture picture) {
        try {
            Picture createdPicture = pictureService.createPicture(picture);
            return ResponseEntity.status(HttpStatus.OK).body(createdPicture);
        } catch (Exception e) {
            log.error("Adding picture {} failed because {}", picture, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Picture>> getAllPictures() {
        try {
            List<Picture> allPlaces = pictureService.getAllPictures();
            return ResponseEntity.status(HttpStatus.OK).body(allPlaces);
        } catch (Exception e) {
            log.error("Retrieving picture list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}