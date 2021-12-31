package com.dnd.Grimoire.model;

import lombok.Data;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "pc")
@Data
public class Pc extends Entity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "race")
    private String race;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "hero_class")
    private String heroClass;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="campaign_id", nullable=false)
    private Campaign campaign;
}
