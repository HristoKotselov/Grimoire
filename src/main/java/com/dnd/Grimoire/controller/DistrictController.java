package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.District;
import com.dnd.Grimoire.service.DistrictService;
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
@RequestMapping("api/district")
@AllArgsConstructor
public class DistrictController {

    @NonNull
    private final DistrictService districtService;

    @GetMapping("/{id}")
    public ResponseEntity<District> getDistrictById(@PathVariable("id") final Long districtId) {
        try {
            final Optional<District> district = districtService.getDistrictById(districtId);
            if (district.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(district.get());
            } else {
                log.error("District with id {} was not found", districtId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving district with id {} failed because {}", districtId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<District> createDistrict(@RequestBody final District district) {
        try {
            District createdDistrict = districtService.createDistrict(district);
            return ResponseEntity.status(HttpStatus.OK).body(createdDistrict);
        } catch (Exception e) {
            log.error("Adding district {} failed because {}", district, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<District>> getAllDistricts() {
        try {
            List<District> allDistricts = districtService.getAllDistricts();
            return ResponseEntity.status(HttpStatus.OK).body(allDistricts);
        } catch (Exception e) {
            log.error("Retrieving district list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}