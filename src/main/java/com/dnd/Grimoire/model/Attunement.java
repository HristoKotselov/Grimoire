package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "attunement")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"required", "visibilities"})
@ToString(exclude = {"required", "visibilities"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Attunement implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "attunement_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attunement_seq")
    private Long attunementId;

    @Column(name = "required")
    private boolean required;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "attument_visibility",
            joinColumns = @JoinColumn(name = "attument_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;
}
