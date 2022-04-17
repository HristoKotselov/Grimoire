package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Pc;
import com.dnd.Grimoire.repository.PcRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PcService {

    @NonNull
    private final PcRepository pcRepository;

    public Optional<Pc> getPcById(Long id) {
        return pcRepository.findById(id);
    }

    public Pc createPc(Pc pc) {
        return pcRepository.save(pc);
    }

    public void deletePc(Pc pc) {
        pcRepository.delete(pc);
    }

    public void deletePcById(Long id) {
        pcRepository.deleteById(id);
    }

    public long getTotalPcCount() {
        return pcRepository.count();
    }

    public List<Pc> getAllPcs() {
        return pcRepository.findAll();
    }
}
