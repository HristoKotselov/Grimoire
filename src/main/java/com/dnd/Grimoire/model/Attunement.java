package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "attunement")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"required", "visibilities"})
@ToString(exclude = {"required", "visibilities"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Attunement {

    @Id
    @Column(name = "attunement_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attunementId;

    @Column(name = "required")
    private boolean required;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "attument_visibility",
            joinColumns = @JoinColumn(name = "attument_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;
}
