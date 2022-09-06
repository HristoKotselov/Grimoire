package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Campaign;
import com.dnd.Grimoire.model.Pc;
import com.dnd.Grimoire.model.Visibility;
import com.dnd.Grimoire.model.VisibilityType;
import com.dnd.Grimoire.repository.CampaignRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CampaignService {

    @NonNull
    private final CampaignRepository campaignRepository;

    @NonNull
    private final VisibilityService visibilityService;

    @NonNull
    private final PcService pcService;

    public Optional<Campaign> getCampaignById(Long id) {
        return campaignRepository.findById(id);
    }

    public Campaign getCampaignByName(String name) {
        return campaignRepository.findByName(name);
    }

    public Campaign createCampaign(Campaign campaign) {
        Campaign createdCampaign = campaignRepository.save(campaign);
        Pc dmPc = pcService.createDmPc(createdCampaign);
        visibilityService.createVisibility(Visibility.builder()
                .campaign(createdCampaign)
                .owner(dmPc)
                .visibilityType(VisibilityType.DM)
                .build());
        visibilityService.createVisibility(Visibility.builder()
                .campaign(createdCampaign)
                .owner(dmPc)
                .visibilityType(VisibilityType.PARTY)
                .build());
        return createdCampaign;
    }

    public void deleteCampaign(Campaign campaign) {
        campaignRepository.delete(campaign);
    }

    public void deleteCampaignById(Long id) {
        campaignRepository.deleteById(id);
    }

    public long getTotalCampaignCount() {
        return campaignRepository.count();
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }
}
