package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Plane;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaneRepository extends JpaRepository<Plane, Long> {

    @NonNull List<Plane> findAll();
}