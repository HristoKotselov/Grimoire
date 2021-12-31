package com.dnd.Grimoire.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "npc")
@Data
public class Npc extends Entity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "race")
    private String race;

    @Column(name = "race_visibility")
    private Visibility raceVisibility;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "gender_visibility")
    private Visibility genderVisibility;

    @OneToMany(mappedBy="npc")
    private List<Alias> aliases;

    @OneToMany(mappedBy="npc")
    private List<Affiliation> affiliations;

    @OneToMany(mappedBy="npc")
    private List<Location> locations;
}
