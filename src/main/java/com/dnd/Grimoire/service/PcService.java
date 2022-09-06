package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.*;
import com.dnd.Grimoire.repository.PcRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PcService {

    @NonNull
    private final PcRepository pcRepository;

    @NonNull
    private final AccountService accountService;

    @NonNull
    private final VisibilityService visibilityService;

    @NonNull
    private final TagService tagService;

    @NonNull
    private final AffiliationService affiliationService;

    public Optional<Pc> getPcById(Long id) {
        return pcRepository.findById(id);
    }

    public Pc getPcByNameAndCampaign(String name, Campaign campaign) {
        return pcRepository.findPcByNameAndCampaign(name, campaign);
    }

    public Pc createDmPc(Campaign campaign) {
        return pcRepository.save(Pc.builder().heroClass("DM").campaign(campaign).owner(campaign.getOwner()).name(campaign.getName()).build());
    }

    public Pc createPc(Pc pc) {
        Account existingAccount = accountService.getExistingAccount(pc.getOwner());
        if (existingAccount != null) {
            pc.setOwner(existingAccount);
//            pc.setCampaign(campaign);
            if (pc.getTags() != null) {
                pc.setTags(tagService.getExistingTagsForEntityImport(pc.getTags()));
            }
            if (pc.getAffiliations() != null) {
                pc.setAffiliations(affiliationService.getExistingAffiliationsForEntityImport(pc.getAffiliations()));
            }
            Pc createdPc = pcRepository.save(pc);
            visibilityService.createVisibility(Visibility.builder()
                    .campaign(createdPc.getCampaign())
                    .owner(createdPc)
                    .visibilityType(VisibilityType.PLAYER)
                    .build());
            visibilityService.createVisibility(Visibility.builder()
                    .campaign(createdPc.getCampaign())
                    .owner(createdPc)
                    .visibilityType(VisibilityType.PARTY)
                    .build());
            return createdPc;
        }
        return null;
    }

    public void deletePc(Pc pc) {
        pcRepository.delete(pc);
    }

    public void deletePcById(Long id) {
        pcRepository.deleteById(id);
    }

    public long getTotalPcCount() {
        return pcRepository.count();
    }

    public List<Pc> getAllPcs() {
        return pcRepository.findAll();
    }
}
