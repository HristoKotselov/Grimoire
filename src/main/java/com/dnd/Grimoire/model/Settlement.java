package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "settlement")
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(exclude = {"districts", "region"}, callSuper = true)
@ToString(exclude = {"districts", "region"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonTypeName("Settlement")
public class Settlement extends BaseEntity {

    @OneToMany(mappedBy="settlement", cascade = CascadeType.ALL)
    private List<District> districts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="region_id", nullable=false)
    private Region region;
}
