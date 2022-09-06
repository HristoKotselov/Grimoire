package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "monster")
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(exclude = {"locations"}, callSuper = true)
@ToString(exclude = {"locations"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonTypeName("Monster")
public class Monster extends BaseEntity {

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "monster_location",
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    private List<Location> locations;
}
