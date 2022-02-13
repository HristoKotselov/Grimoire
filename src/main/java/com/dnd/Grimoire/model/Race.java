package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "race")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"pc", "npc", "visibilities"})
@ToString(exclude = {"pc", "npc", "visibilities"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Race {

    @Id
    @Column(name = "race_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long raceId;

    @Column(name = "race_name")
    private String raceName;

    @OneToOne(mappedBy = "race")
    private Pc pc;

    @OneToOne(mappedBy = "race")
    private Npc npc;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "race_visibility",
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;
}
