package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "plane")
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(exclude = {"continents"}, callSuper = true)
@ToString(exclude = {"continents"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonTypeName("Plane")
public class Plane extends BaseEntity {

    @OneToMany(mappedBy="plane", cascade = CascadeType.ALL)
    private List<Continent> continents;
}
