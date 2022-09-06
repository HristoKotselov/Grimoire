package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Event;
import com.dnd.Grimoire.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventService {

    @NonNull
    private final EventRepository eventRepository;

    @NonNull
    private final TagService tagService;

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event) {
        if (event.getTags() != null) {
            event.setTags(tagService.getExistingTagsForEntityImport(event.getTags()));
        }
        return eventRepository.save(event);
    }

    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }

    public long getTotalEventCount() {
        return eventRepository.count();
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
