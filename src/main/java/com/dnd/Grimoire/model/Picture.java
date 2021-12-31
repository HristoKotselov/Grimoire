package com.dnd.Grimoire.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "picture")
@Data
public class Picture {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "label")
    private String label;

    @Column(name = "filepath")
    private String filepath;

    @Column(name = "visibility")
    private Visibility visibility;
}
