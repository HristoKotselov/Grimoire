package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Item;
import com.dnd.Grimoire.service.ItemService;
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
@RequestMapping("api/item")
@AllArgsConstructor
public class ItemController {

    @NonNull
    private final ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") final Long itemId) {
        try {
            final Optional<Item> item = itemService.getItemById(itemId);
            if (item.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(item.get());
            } else {
                log.error("Item with id {} was not found", itemId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving item with id {} failed because {}", itemId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Item> createItem(@RequestBody final Item item) {
        try {
            Item createdItem = itemService.createItem(item);
            return ResponseEntity.status(HttpStatus.OK).body(createdItem);
        } catch (Exception e) {
            log.error("Adding item {} failed because {}", item, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Item>> getAllItems() {
        try {
            List<Item> allItems = itemService.getAllItems();
            return ResponseEntity.status(HttpStatus.OK).body(allItems);
        } catch (Exception e) {
            log.error("Retrieving item list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}