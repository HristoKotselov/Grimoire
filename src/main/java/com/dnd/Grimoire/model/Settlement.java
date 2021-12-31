package com.dnd.Grimoire.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "settlement")
@Data
public class Settlement extends Entity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy="settlement")
    private List<District> districts;

    @ManyToOne
    @JoinColumn(name="region_id", nullable=false)
    private Region region;
}
