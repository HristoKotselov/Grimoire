package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Settlement;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettlementRepository extends JpaRepository<Settlement, Long> {

    @NonNull List<Settlement> findAll();
}