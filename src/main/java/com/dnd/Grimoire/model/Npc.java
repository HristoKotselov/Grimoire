package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "npc")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"race", "gender", "aliases", "locations", "affiliations"}, callSuper = true)
@ToString(exclude = {"race", "gender", "aliases", "locations", "affiliations"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@PrimaryKeyJoinColumn(name = "npc_id")
public class Npc extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id")
    private Race race;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Alias> aliases;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Location> locations;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "npc_affiliation",
            joinColumns = @JoinColumn(name = "npc_id"),
            inverseJoinColumns = @JoinColumn(name = "affiliation_id"))
    private List<Affiliation> affiliations;
}
