package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Item;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @NonNull List<Item> findAll();
}