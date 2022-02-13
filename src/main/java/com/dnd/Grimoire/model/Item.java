package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"itemType", "rarity", "attunement", "itemValue"}, callSuper = true)
@ToString(exclude = {"itemType", "rarity", "attunement", "itemValue"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@PrimaryKeyJoinColumn(name = "item_id")
public class Item extends BaseEntity {

    @Column(name = "item_type")
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rarity_id")
    private Rarity rarity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attunement_id")
    private Attunement attunement;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_value_id")
    private ItemValue itemValue;

}
