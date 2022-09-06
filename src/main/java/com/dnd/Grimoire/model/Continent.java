package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "continent")
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(exclude = {"regions", "plane"}, callSuper = true)
@ToString(exclude = {"regions", "plane"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonTypeName("Continent")
public class Continent extends BaseEntity {

    @OneToMany(mappedBy="continent", cascade = CascadeType.ALL)
    private List<Region> regions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="plane_id", nullable=false)
    private Plane plane;
}
