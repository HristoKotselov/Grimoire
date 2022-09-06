package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "item")
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(exclude = {"itemType", "rarity", "attunement", "itemValue"}, callSuper = true)
@ToString(exclude = {"itemType", "rarity", "attunement", "itemValue"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonTypeName("Item")
public class Item extends BaseEntity {

    @Column(name = "item_type")
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rarity_id")
    private Rarity rarity;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "attunement_id")
    private Attunement attunement;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_value_id")
    private ItemValue itemValue;

}
