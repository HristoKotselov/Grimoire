package com.dnd.Grimoire.model;

import lombok.Data;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "item")
@Data
public class Item extends Entity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "item_type")
    private ItemType itemType;

    @Column(name = "rarity")
    private Rarity rarity;

    @Column(name = "rarity_visibility")
    private Visibility rarityVisibility;

    @Column(name = "attunement")
    private boolean attunement;

    @Column(name = "value")
    private String value;
}
