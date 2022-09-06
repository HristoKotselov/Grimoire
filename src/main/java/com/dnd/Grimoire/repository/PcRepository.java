package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Campaign;
import com.dnd.Grimoire.model.Pc;
import com.dnd.Grimoire.model.Visibility;
import com.dnd.Grimoire.model.VisibilityType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PcRepository extends JpaRepository<Pc, Long> {

    @NonNull List<Pc> findAll();

    @Query("SELECT pc FROM Pc pc WHERE pc.name = :name AND pc.campaign = :campaign")
    Pc findPcByNameAndCampaign(@Param("name") String name, @Param("campaign") Campaign campaign);
}
