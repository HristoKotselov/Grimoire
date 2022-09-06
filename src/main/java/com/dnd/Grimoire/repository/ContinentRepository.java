package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Continent;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContinentRepository extends JpaRepository<Continent, Long> {

    @NonNull List<Continent> findAll();
}