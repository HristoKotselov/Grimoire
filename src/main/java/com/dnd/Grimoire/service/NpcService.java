package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Alias;
import com.dnd.Grimoire.model.Npc;
import com.dnd.Grimoire.repository.NpcRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NpcService {

    @NonNull
    private final NpcRepository npcRepository;

    @NonNull
    private final TagService tagService;

    @NonNull
    private final LocationService locationService;

    @NonNull
    private final AffiliationService affiliationService;

    public Optional<Npc> getNpcById(Long id) {
        return npcRepository.findById(id);
    }

    public Npc createNpc(Npc npc) {
        if (npc.getAffiliations() != null) {
            npc.setAffiliations(affiliationService.getExistingAffiliationsForEntityImport(npc.getAffiliations()));
        }
        if (npc.getTags() != null) {
            npc.setTags(tagService.getExistingTagsForEntityImport(npc.getTags()));
        }
        if (npc.getLocations() != null) {
            npc.setLocations(locationService.getExistingLocationsForEntityImport(npc.getLocations()));
        }
        return npcRepository.save(npc);
    }

    public void deleteNpc(Npc npc) {
        npcRepository.delete(npc);
    }

    public void deleteNpcById(Long id) {
        npcRepository.deleteById(id);
    }

    public long getTotalNpcCount() {
        return npcRepository.count();
    }

    public List<Npc> getAllNpcs() {
        return npcRepository.findAll();
    }
}
