package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "settlement")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"districts", "region"}, callSuper = true)
@ToString(exclude = {"districts", "region"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Settlement extends BaseEntity {

    @OneToMany(mappedBy="settlement", cascade = CascadeType.ALL)
    private List<District> districts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="region_id", nullable=false)
    private Region region;
}
