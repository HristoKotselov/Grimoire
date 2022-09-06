package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Item;
import com.dnd.Grimoire.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {

    @NonNull
    private final ItemRepository itemRepository;

    @NonNull
    private final TagService tagService;

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Item createItem(Item item) {
        if (item.getTags() != null) {
            item.setTags(tagService.getExistingTagsForEntityImport(item.getTags()));
        }
        return itemRepository.save(item);
    }

    public void deleteItem(Item item) {
        itemRepository.delete(item);
    }

    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }

    public long getTotalItemCount() {
        return itemRepository.count();
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}