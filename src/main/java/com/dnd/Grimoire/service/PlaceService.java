package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Place;
import com.dnd.Grimoire.repository.PlaceRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlaceService {

    @NonNull
    private final PlaceRepository placeRepository;

    @NonNull
    private final TagService tagService;

    public Optional<Place> getPlaceById(Long id) {
        return placeRepository.findById(id);
    }

    public Place createPlace(Place place) {
        if (place.getTags() != null) {
            place.setTags(tagService.getExistingTagsForEntityImport(place.getTags()));
        }
        return placeRepository.save(place);
    }

    public void deletePlace(Place place) {
        placeRepository.delete(place);
    }

    public void deletePlaceById(Long id) {
        placeRepository.deleteById(id);
    }

    public long getTotalPlaceCount() {
        return placeRepository.count();
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }
}
