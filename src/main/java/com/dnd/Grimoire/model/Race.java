package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "race")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"visibilities"})
@ToString(exclude = {"visibilities"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Race implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "race_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "race_seq")
    private Long raceId;

    @Column(name = "race_name")
    private String raceName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "race_visibility",
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;
}
