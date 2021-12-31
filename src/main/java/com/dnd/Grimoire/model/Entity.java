package com.dnd.Grimoire.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public abstract class Entity {

    @Column(name = "name")
    private String name;
    private Tag[] tags;
    private Picture[] pictures;
    private Description[] descriptions;
    private Visibility visibility;
}
