package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "monster")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"locations"}, callSuper = true)
@ToString(exclude = {"locations"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Monster extends BaseEntity {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "monster_location",
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    private List<Location> locations;
}
