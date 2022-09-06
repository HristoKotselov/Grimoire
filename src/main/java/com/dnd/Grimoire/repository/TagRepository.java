package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Tag;
import com.dnd.Grimoire.model.Visibility;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @NonNull List<Tag> findAll();

    @Query("SELECT t FROM Tag t WHERE t.name = :name AND t.visibility = :visibility")
    Tag findByNameAndVisibility(@Param("name") String name, @Param("visibility") Visibility visibility);
}