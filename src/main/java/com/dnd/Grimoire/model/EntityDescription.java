package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "base_entity_description")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"baseEntity", "descriptionText", "visibility"})
@ToString(exclude = {"baseEntity", "descriptionText", "visibility"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EntityDescription {

    @Id
    @Column(name = "description_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long descriptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_entity_id")
    private BaseEntity baseEntity;

    @Lob
    @Column(name = "description_text")
    private String descriptionText;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visibility_id")
    private Visibility visibility;
}
