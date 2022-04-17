package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "location")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"visibilities", "npcs", "monsters"})
@ToString(exclude = {"visibilities", "npcs", "monsters"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Location {

    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long locationId;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "locataion_visibility",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;

    @ManyToMany(mappedBy = "locations", fetch = FetchType.LAZY)
    private List<Npc> npcs;

    @ManyToMany(mappedBy = "locations", fetch = FetchType.LAZY)
    private List<Monster> monsters;
}
