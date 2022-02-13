package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "region")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"settlements", "continent"}, callSuper = true)
@ToString(exclude = {"settlements", "continent"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@PrimaryKeyJoinColumn(name = "region_id")
public class Region extends BaseEntity {

    @OneToMany(mappedBy="region", cascade = CascadeType.ALL)
    private List<Settlement> settlements;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="continent_id", nullable=false)
    private Continent continent;
}
