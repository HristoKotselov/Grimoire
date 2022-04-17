package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "district")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"places", "settlement"}, callSuper = true)
@ToString(exclude = {"places", "settlement"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class District extends BaseEntity {

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Place> places;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "settlement_id", nullable=false)
    private Settlement settlement;
}
