package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Settlement;
import com.dnd.Grimoire.repository.SettlementRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SettlementService {

    @NonNull
    private final SettlementRepository settlementRepository;

    @NonNull
    private final TagService tagService;

    public Optional<Settlement> getSettlementById(Long id) {
        return settlementRepository.findById(id);
    }

    public Settlement createSettlement(Settlement settlement) {
        if (settlement.getTags() != null) {
            settlement.setTags(tagService.getExistingTagsForEntityImport(settlement.getTags()));
        }
        return settlementRepository.save(settlement);
    }

    public void deleteSettlement(Settlement settlement) {
        settlementRepository.delete(settlement);
    }

    public void deleteSettlementById(Long id) {
        settlementRepository.deleteById(id);
    }

    public long getTotalSettlementCount() {
        return settlementRepository.count();
    }

    public List<Settlement> getAllSettlements() {
        return settlementRepository.findAll();
    }
}
