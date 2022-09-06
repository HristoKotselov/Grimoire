package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Location;
import com.dnd.Grimoire.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationService {

    @NonNull
    private final LocationRepository locationRepository;

    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(Location location) {
        locationRepository.delete(location);
    }

    public void deleteLocationById(Long id) {
        locationRepository.deleteById(id);
    }

    public long getTotalLocationCount() {
        return locationRepository.count();
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public List<Location> getExistingLocationsForEntityImport(List<Location> locations) {
        List<Location> newList = new ArrayList<>();
        for (Location location: locations) {
            Location existingLocation = locationRepository.findByName(location.getName());
            newList.add(Objects.requireNonNullElse(existingLocation, location));
        }

        return newList;
    }
}
