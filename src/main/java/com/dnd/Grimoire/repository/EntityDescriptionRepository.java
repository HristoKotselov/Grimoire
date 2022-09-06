package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.EntityDescription;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntityDescriptionRepository extends JpaRepository<EntityDescription, Long> {

    @NonNull List<EntityDescription> findAll();
}