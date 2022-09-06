package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "region")
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(exclude = {"settlements", "continent"}, callSuper = true)
@ToString(exclude = {"settlements", "continent"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonTypeName("Region")
public class Region extends BaseEntity {

    @OneToMany(mappedBy="region", cascade = CascadeType.ALL)
    private List<Settlement> settlements;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="continent_id", nullable=false)
    private Continent continent;
}
