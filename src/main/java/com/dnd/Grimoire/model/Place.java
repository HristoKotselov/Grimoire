package com.dnd.Grimoire.model;

import lombok.Data;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "place")
@Data
public class Place extends Entity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="district_id", nullable=false)
    private District district;
}
