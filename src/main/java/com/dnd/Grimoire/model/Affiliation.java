package com.dnd.Grimoire.model;

import lombok.Data;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "affiliation")
@Data
public class Affiliation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "visibility")
    private Visibility visibility;

    @ManyToOne
    @JoinColumn(name="npc_id", nullable=false)
    private Npc npc;
}
