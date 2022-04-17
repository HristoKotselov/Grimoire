package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item_value")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"value", "visibilities"})
@ToString(exclude = {"value", "visibilities"})
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "item_value_visibility",
            joinColumns = @JoinColumn(name = "item_value_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;
}
