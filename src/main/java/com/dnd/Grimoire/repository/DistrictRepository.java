package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.District;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {

    @NonNull List<District> findAll();
}