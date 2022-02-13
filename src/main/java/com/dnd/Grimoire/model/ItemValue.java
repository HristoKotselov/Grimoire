package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item_value")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"value", "item", "visibilities"})
@ToString(exclude = {"value", "item", "visibilities"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemValue {

    @Id
    @Column(name = "item_value_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long valueId;

    @Column(name = "value")
    private String value;

    @OneToOne(mappedBy = "itemValue")
    private Item item;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "item_value_visibility",
            joinColumns = @JoinColumn(name = "item_value_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;
}
