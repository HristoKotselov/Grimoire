package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "location")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"visibilities", "npcs", "monsters"})
@ToString(exclude = {"visibilities", "npcs", "monsters"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Location implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
    private Long locationId;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "locations", fetch = FetchType.LAZY)
    private List<Npc> npcs;

    @JsonIgnore
    @ManyToMany(mappedBy = "locations", fetch = FetchType.LAZY)
    private List<Monster> monsters;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "location_visibility",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;
}
