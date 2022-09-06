package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Region;
import com.dnd.Grimoire.repository.RegionRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegionService {

    @NonNull
    private final RegionRepository regionRepository;

    @NonNull
    private final TagService tagService;

    public Optional<Region> getRegionById(Long id) {
        return regionRepository.findById(id);
    }

    public Region createRegion(Region region) {
        if (region.getTags() != null) {
            region.setTags(tagService.getExistingTagsForEntityImport(region.getTags()));
        }
        return regionRepository.save(region);
    }

    public void deleteRegion(Region region) {
        regionRepository.delete(region);
    }

    public void deleteRegionById(Long id) {
        regionRepository.deleteById(id);
    }

    public long getTotalRegionCount() {
        return regionRepository.count();
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }
}
