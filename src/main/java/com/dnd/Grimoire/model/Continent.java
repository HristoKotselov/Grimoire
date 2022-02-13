package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "continent")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"regions", "plane"}, callSuper = true)
@ToString(exclude = {"regions", "plane"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@PrimaryKeyJoinColumn(name = "continent_id")
public class Continent extends BaseEntity {

    @OneToMany(mappedBy="continent", cascade = CascadeType.ALL)
    private List<Region> regions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="plane_id", nullable=false)
    private Plane plane;
}
