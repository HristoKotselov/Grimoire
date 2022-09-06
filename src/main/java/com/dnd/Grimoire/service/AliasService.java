package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.*;
import com.dnd.Grimoire.repository.AliasRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AliasService {

    @NonNull
    private final AliasRepository aliasRepository;

    public Optional<Alias> getAliasById(Long id) {
        return aliasRepository.findById(id);
    }

    public Alias createAlias(Alias alias) {
        return aliasRepository.save(alias);
    }

    public List<Alias> createAliases(List<Alias> aliases) {
        return aliasRepository.saveAll(aliases);
    }

    public void deleteAlias(Alias alias) {
        aliasRepository.delete(alias);
    }

    public void deleteAliasById(Long id) {
        aliasRepository.deleteById(id);
    }

    public long getTotalAliasCount() {
        return aliasRepository.count();
    }

    public List<Alias> getAllAliases() {
        return aliasRepository.findAll();
    }

}
