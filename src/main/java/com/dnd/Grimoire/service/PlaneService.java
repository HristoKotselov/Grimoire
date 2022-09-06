package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Plane;
import com.dnd.Grimoire.repository.PlaneRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlaneService {

    @NonNull
    private final PlaneRepository planeRepository;

    @NonNull
    private final TagService tagService;

    public Optional<Plane> getPlaneById(Long id) {
        return planeRepository.findById(id);
    }

    public Plane createPlane(Plane plane) {
        if (plane.getTags() != null) {
            plane.setTags(tagService.getExistingTagsForEntityImport(plane.getTags()));
        }
        return planeRepository.save(plane);
    }

    public void deletePlane(Plane plane) {
        planeRepository.delete(plane);
    }

    public void deletePlaneById(Long id) {
        planeRepository.deleteById(id);
    }

    public long getTotalPlaneCount() {
        return planeRepository.count();
    }

    public List<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }
}
