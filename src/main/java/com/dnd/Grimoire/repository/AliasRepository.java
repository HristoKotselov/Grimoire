package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Alias;
import com.dnd.Grimoire.model.BaseEntity;
import com.dnd.Grimoire.model.Tag;
import com.dnd.Grimoire.model.Visibility;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AliasRepository extends JpaRepository<Alias, Long> {

    @NonNull List<Alias> findAll();

    @Query("SELECT a FROM Alias a WHERE a.baseEntity = :baseEntity")
    List<Alias> findByBaseEntityName(@Param("baseEntity") BaseEntity baseEntity);

    @Query("SELECT a FROM Alias a WHERE a.baseEntity = :baseEntity AND a.visibility = :visibility")
    List<Alias> findByNameAndVisibility(@Param("baseEntity") BaseEntity baseEntity, @Param("visibility") Visibility visibility);
}