package com.dnd.Grimoire.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "campaign")
@Data
public class Campaign {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    private User owner;

    @OneToMany(mappedBy="campaign")
    private List<Pc> players;
}
