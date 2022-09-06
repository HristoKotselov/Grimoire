package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Monster;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonsterRepository extends JpaRepository<Monster, Long> {

    @NonNull List<Monster> findAll();
}