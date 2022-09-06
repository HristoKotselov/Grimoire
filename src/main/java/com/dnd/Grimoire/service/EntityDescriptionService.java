package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.EntityDescription;
import com.dnd.Grimoire.repository.EntityDescriptionRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EntityDescriptionService {

    @NonNull
    private final EntityDescriptionRepository entityDescriptionRepository;

    public Optional<EntityDescription> getEntityDescriptionById(Long id) {
        return entityDescriptionRepository.findById(id);
    }

    public EntityDescription createEntityDescription(EntityDescription entityDescription) {
        return entityDescriptionRepository.save(entityDescription);
    }

    public void deleteEntityDescription(EntityDescription entityDescription) {
        entityDescriptionRepository.delete(entityDescription);
    }

    public void deleteEntityDescriptionById(Long id) {
        entityDescriptionRepository.deleteById(id);
    }

    public long getTotalEntityDescriptionCount() {
        return entityDescriptionRepository.count();
    }

    public List<EntityDescription> getAllEntityDescriptions() {
        return entityDescriptionRepository.findAll();
    }
}
