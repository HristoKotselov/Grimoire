package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Location;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @NonNull List<Location> findAll();

    @Query("SELECT l FROM Location l WHERE l.name = :name")
    Location findByName(@Param("name") String name);
}