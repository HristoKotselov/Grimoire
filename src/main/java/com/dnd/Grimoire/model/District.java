package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "district")
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(exclude = {"places", "settlement"}, callSuper = true)
@ToString(exclude = {"places", "settlement"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonTypeName("District")
public class District extends BaseEntity {

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Place> places;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "settlement_id", nullable=false)
    private Settlement settlement;
}
