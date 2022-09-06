package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.District;
import com.dnd.Grimoire.repository.DistrictRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DistrictService {

    @NonNull
    private final DistrictRepository districtRepository;

    @NonNull
    private final TagService tagService;

    public Optional<District> getDistrictById(Long id) {
        return districtRepository.findById(id);
    }

    public District createDistrict(District district) {
        if (district.getTags() != null) {
            district.setTags(tagService.getExistingTagsForEntityImport(district.getTags()));
        }
        return districtRepository.save(district);
    }

    public void deleteDistrict(District district) {
        districtRepository.delete(district);
    }

    public void deleteDistrictById(Long id) {
        districtRepository.deleteById(id);
    }

    public long getTotalDistrictCount() {
        return districtRepository.count();
    }

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }
}
