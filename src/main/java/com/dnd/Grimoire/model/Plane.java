package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "plane")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"continents"}, callSuper = true)
@ToString(exclude = {"continents"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Plane extends BaseEntity {

    @OneToMany(mappedBy="plane", cascade = CascadeType.ALL)
    private List<Continent> continents;
}
