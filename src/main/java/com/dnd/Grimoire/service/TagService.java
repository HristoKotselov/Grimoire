package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Tag;
import com.dnd.Grimoire.repository.TagRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagService {

    @NonNull
    private final TagRepository tagRepository;

    public Optional<Tag> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public void deleteTag(Tag tag) {
        tagRepository.delete(tag);
    }

    public void deleteTagById(Long id) {
        tagRepository.deleteById(id);
    }

    public long getTotalTagCount() {
        return tagRepository.count();
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public List<Tag> getExistingTagsForEntityImport(List<Tag> tags) {
        List<Tag> newList = new ArrayList<>();
        for (Tag tag: tags) {
            Tag existingTag = tagRepository.findByNameAndVisibility(tag.getName(), tag.getVisibility());
                newList.add(Objects.requireNonNullElse(existingTag, tag));
        }

        return newList;
    }
}
