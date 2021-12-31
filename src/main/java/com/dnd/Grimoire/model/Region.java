package com.dnd.Grimoire.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "region")
@Data
public class Region extends Entity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy="region")
    private List<Settlement> settlements;

    @ManyToOne
    @JoinColumn(name="continent_id", nullable=false)
    private Continent continent;
}
