package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Campaign;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @NonNull List<Campaign> findAll();

    @Query("SELECT c FROM Campaign c WHERE c.name = :name")
    Campaign findByName(@Param("name") String name);
}
