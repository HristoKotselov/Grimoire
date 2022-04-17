package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Campaign;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @NonNull List<Campaign> findAll();
}
