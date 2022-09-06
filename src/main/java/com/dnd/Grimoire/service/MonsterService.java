package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Monster;
import com.dnd.Grimoire.repository.MonsterRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MonsterService {

    @NonNull
    private final MonsterRepository monsterRepository;

    @NonNull
    private final TagService tagService;

    @NonNull
    private final LocationService locationService;

    public Optional<Monster> getMonsterById(Long id) {
        return monsterRepository.findById(id);
    }

    public Monster createMonster(Monster monster) {
        if (monster.getTags() != null) {
            monster.setTags(tagService.getExistingTagsForEntityImport(monster.getTags()));
        }
        if (monster.getLocations() != null) {
            monster.setLocations(locationService.getExistingLocationsForEntityImport(monster.getLocations()));
        }
        return monsterRepository.save(monster);
    }

    public void deleteMonster(Monster monster) {
        monsterRepository.delete(monster);
    }

    public void deleteMonsterById(Long id) {
        monsterRepository.deleteById(id);
    }

    public long getTotalMonsterCount() {
        return monsterRepository.count();
    }

    public List<Monster> getAllMonsters() {
        return monsterRepository.findAll();
    }
}
