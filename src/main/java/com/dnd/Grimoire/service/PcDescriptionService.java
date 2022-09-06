package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.PcDescription;
import com.dnd.Grimoire.repository.PcDescriptionRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PcDescriptionService {

    @NonNull
    private final PcDescriptionRepository pcDescriptionRepository;

    public Optional<PcDescription> getPcDescriptionById(Long id) {
        return pcDescriptionRepository.findById(id);
    }

    public PcDescription createPcDescription(PcDescription pcDescription) {
        return pcDescriptionRepository.save(pcDescription);
    }

    public void deletePcDescription(PcDescription pcDescription) {
        pcDescriptionRepository.delete(pcDescription);
    }

    public void deletePcDescriptionById(Long id) {
        pcDescriptionRepository.deleteById(id);
    }

    public long getTotalPcDescriptionCount() {
        return pcDescriptionRepository.count();
    }

    public List<PcDescription> getAllPcDescriptions() {
        return pcDescriptionRepository.findAll();
    }
}
