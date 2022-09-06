package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Affiliation;
import com.dnd.Grimoire.model.BaseEntity;
import com.dnd.Grimoire.model.Tag;
import com.dnd.Grimoire.model.Visibility;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AffiliationRepository extends JpaRepository<Affiliation, Long> {

    @NonNull List<Affiliation> findAll();

    @Query("SELECT a FROM Affiliation a WHERE a.name = :name AND a.visibility = :visibility")
    Affiliation findByNameAndVisibility(@Param("name") String name, @Param("visibility") Visibility visibility);
}