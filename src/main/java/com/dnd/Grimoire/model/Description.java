package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "description")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"descriptionText", "visibilities"})
@ToString(exclude = {"descriptionText", "visibilities"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Description {

    @Id
    @Column(name = "description_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long descriptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_entity_id", nullable = false)
    private BaseEntity baseEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable=false)
    private Account owner;

    @Lob
    @Column(name = "description_text")
    private String descriptionText;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "description_visibility",
            joinColumns = @JoinColumn(name = "description_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;
}
