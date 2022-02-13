package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "npc")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"locations"}, callSuper = true)
@ToString(exclude = {"locations"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@PrimaryKeyJoinColumn(name = "monster_id")
public class Monster extends BaseEntity {

    @OneToMany(mappedBy = "monster")
    private List<Location> locations;
}
