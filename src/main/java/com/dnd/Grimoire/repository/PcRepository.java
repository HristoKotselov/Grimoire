package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Pc;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PcRepository extends JpaRepository<Pc, Long> {

    @NonNull List<Pc> findAll();
}
