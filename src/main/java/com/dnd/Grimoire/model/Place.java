package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;

@Entity
@Table(name = "place")
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(exclude = {"district"}, callSuper = true)
@ToString(exclude = {"district"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonTypeName("Place")
public class Place extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="district_id", nullable=false)
    private District district;
}
