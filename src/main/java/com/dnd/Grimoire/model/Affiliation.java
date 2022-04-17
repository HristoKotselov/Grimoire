package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "affiliation")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"visibilities", "npcs"})
@ToString(exclude = {"visibilities", "npcs"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Affiliation {

    @Id
    @Column(name = "affiliation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long affiliationId;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "affiliation_visibility",
            joinColumns = @JoinColumn(name = "affiliation_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;

    @ManyToMany(mappedBy = "affiliations", fetch = FetchType.LAZY)
    private List<Npc> npcs;
}
