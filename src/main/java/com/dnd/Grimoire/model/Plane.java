package com.dnd.Grimoire.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "plane")
@Data
public class Plane extends Entity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy="plane")
    private List<Continent> continents;
}
