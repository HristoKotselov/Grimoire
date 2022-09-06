package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Place;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @NonNull List<Place> findAll();
}