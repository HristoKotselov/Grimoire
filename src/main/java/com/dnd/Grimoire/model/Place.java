package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "place")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"district"}, callSuper = true)
@ToString(exclude = {"district"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@PrimaryKeyJoinColumn(name = "place_id")
public class Place extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="district_id", nullable=false)
    private District district;
}
