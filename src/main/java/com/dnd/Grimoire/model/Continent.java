package com.dnd.Grimoire.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "continent")
@Data
public class Continent extends Entity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy="continent")
    private List<Region> regions;

    @ManyToOne
    @JoinColumn(name="plane_id", nullable=false)
    private Plane plane;
}
