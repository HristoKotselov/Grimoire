package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.*;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisibilityRepository extends JpaRepository<Visibility, Long> {

    @NonNull List<Visibility> findAll();

    @Query("SELECT v FROM Visibility v WHERE v.owner = :owner AND v.visibilityType = :visibilityType")
    Visibility findByOwnerAndType(@Param("owner") Pc owner, @Param("visibilityType") VisibilityType visibilityType);

    @Query("SELECT v FROM Visibility v WHERE v.campaign.campaignId = :campaignId AND v.visibilityType = :visibilityType")
    Visibility findVisibilityByCampaignIdAndType(@Param("campaignId") Long campaignId, @Param("visibilityType") VisibilityType visibilityType);

    @Query("SELECT v FROM Visibility v WHERE v.campaign = :campaign AND v.visibilityType = :visibilityType")
    Visibility findVisibilityByCampaignAndType(@Param("campaign") Campaign campaign, @Param("visibilityType") VisibilityType visibilityType);
}