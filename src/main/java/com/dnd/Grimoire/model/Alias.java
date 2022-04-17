package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "alias")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"visibilities"})
@ToString(exclude = {"visibilities"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Alias {

    @Id
    @Column(name = "alias_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aliasId;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_entity_id")
    private BaseEntity baseEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "alias_visibility",
            joinColumns = @JoinColumn(name = "alias_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;
}
