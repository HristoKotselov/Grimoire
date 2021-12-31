package com.dnd.Grimoire.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "district")
@Data
public class District extends Entity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy="district")
    private List<Place> places;

    @ManyToOne
    @JoinColumn(name="settlement_id", nullable=false)
    private Settlement settlement;
}
