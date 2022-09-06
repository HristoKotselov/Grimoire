package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Picture;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    @NonNull List<Picture> findAll();
}