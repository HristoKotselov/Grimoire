package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.*;
import com.dnd.Grimoire.repository.VisibilityRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VisibilityService {

    @NonNull
    private final VisibilityRepository visibilityRepository;

    public Optional<Visibility> getVisibilityById(Long id) {
        return visibilityRepository.findById(id);
    }

    public Visibility createVisibility(Visibility visibility) {
        return visibilityRepository.save(visibility);
    }

    public void deleteVisibility(Visibility visibility) {
        visibilityRepository.delete(visibility);
    }

    public void deleteVisibilityById(Long id) {
        visibilityRepository.deleteById(id);
    }

    public long getTotalVisibilityCount() {
        return visibilityRepository.count();
    }

    public List<Visibility> getAllVisibilities() {
        return visibilityRepository.findAll();
    }

    public Visibility getVisibilityByOwnerAndType(Pc owner, VisibilityType visibilityType) {
        return visibilityRepository.findByOwnerAndType(owner, visibilityType);
    }

    public Visibility getDmVisibilityByCampaignId(Long campaignId) {
        return visibilityRepository.findVisibilityByCampaignIdAndType(campaignId, VisibilityType.DM);
    }

    public Visibility getDmVisibilityByCampaign(Campaign campaign) {
        return visibilityRepository.findVisibilityByCampaignAndType(campaign, VisibilityType.DM);
    }

    public Visibility getPartyVisibilityByCampaignId(Long campaignId) {
        return visibilityRepository.findVisibilityByCampaignIdAndType(campaignId, VisibilityType.PARTY);
    }

    public Visibility getPartyVisibilityByCampaign(Campaign campaign) {
        return visibilityRepository.findVisibilityByCampaignAndType(campaign, VisibilityType.PARTY);
    }

}
