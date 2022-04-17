package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Npc;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NpcRepository extends JpaRepository<Npc, Long> {

    @NonNull List<Npc> findAll();
}
