package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Campaign;
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

    public Optional<Campaign> getCampaignById(Long id) {
        return campaignRepository.findById(id);
    }

    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
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
