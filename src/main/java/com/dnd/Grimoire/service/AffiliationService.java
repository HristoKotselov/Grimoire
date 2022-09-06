package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Affiliation;
import com.dnd.Grimoire.repository.AffiliationRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AffiliationService {

    @NonNull
    private final AffiliationRepository affiliationRepository;

    public Optional<Affiliation> getAffiliationById(Long id) {
        return affiliationRepository.findById(id);
    }

    public Affiliation createAffiliation(Affiliation affiliation) {
        return affiliationRepository.save(affiliation);
    }

    public void deleteAffiliation(Affiliation affiliation) {
        affiliationRepository.delete(affiliation);
    }

    public void deleteAffiliationById(Long id) {
        affiliationRepository.deleteById(id);
    }

    public long getTotalAffiliationCount() {
        return affiliationRepository.count();
    }

    public List<Affiliation> getAllAffiliations() {
        return affiliationRepository.findAll();
    }

    public List<Affiliation> getExistingAffiliationsForEntityImport(List<Affiliation> affiliations) {
        List<Affiliation> newList = new ArrayList<>();
        for (Affiliation affiliation: affiliations) {
            Affiliation existingAffiliation = affiliationRepository.findByNameAndVisibility(affiliation.getName(), affiliation.getVisibility());
            newList.add(Objects.requireNonNullElse(existingAffiliation, affiliation));
        }

        return newList;
    }
}
