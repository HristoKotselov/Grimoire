package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Event;
import com.dnd.Grimoire.service.EventService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("api/event")
@AllArgsConstructor
public class EventController {

    @NonNull
    private final EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") final Long eventId) {
        try {
            final Optional<Event> plane = eventService.getEventById(eventId);
            if (plane.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(plane.get());
            } else {
                log.error("Event with id {} was not found", eventId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving event with id {} failed because {}", eventId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody final Event event) {
        try {
            Event createdEvent = eventService.createEvent(event);
            return ResponseEntity.status(HttpStatus.OK).body(createdEvent);
        } catch (Exception e) {
            log.error("Adding event {} failed because {}", event, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Event>> getAllEvents() {
        try {
            List<Event> allEvents = eventService.getAllEvents();
            return ResponseEntity.status(HttpStatus.OK).body(allEvents);
        } catch (Exception e) {
            log.error("Retrieving event list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}