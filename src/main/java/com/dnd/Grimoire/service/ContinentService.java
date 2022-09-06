package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Continent;
import com.dnd.Grimoire.repository.ContinentRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContinentService {

    @NonNull
    private final ContinentRepository continentRepository;

    @NonNull
    private final TagService tagService;

    public Optional<Continent> getContinentById(Long id) {
        return continentRepository.findById(id);
    }

    public Continent createContinent(Continent continent) {
        if (continent.getTags() != null) {
            continent.setTags(tagService.getExistingTagsForEntityImport(continent.getTags()));
        }
        return continentRepository.save(continent);
    }

    public void deleteContinent(Continent continent) {
        continentRepository.delete(continent);
    }

    public void deleteContinentById(Long id) {
        continentRepository.deleteById(id);
    }

    public long getTotalContinentCount() {
        return continentRepository.count();
    }

    public List<Continent> getAllContinents() {
        return continentRepository.findAll();
    }
}
