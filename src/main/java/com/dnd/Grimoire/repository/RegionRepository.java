package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Region;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {

    @NonNull List<Region> findAll();
}