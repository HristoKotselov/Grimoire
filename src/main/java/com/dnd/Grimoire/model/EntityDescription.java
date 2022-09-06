package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "base_entity_description")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"baseEntity", "descriptionText", "visibility"})
@ToString(exclude = {"baseEntity", "descriptionText", "visibility"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EntityDescription implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "description_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_description_seq")
    private Long descriptionId;

    @JsonBackReference
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
