package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.PcDescription;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PcDescriptionRepository extends JpaRepository<PcDescription, Long> {

    @NonNull List<PcDescription> findAll();
}