package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Event;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @NonNull List<Event> findAll();
}