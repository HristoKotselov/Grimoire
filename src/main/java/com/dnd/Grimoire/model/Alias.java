package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "alias")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"pc", "baseEntity", "affiliation", "visibility"})
@ToString(exclude = {"pc", "baseEntity", "affiliation", "visibility"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Alias implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "alias_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alias_seq")
    private Long aliasId;

    @Column(name = "name")
    private String name;

    @JsonBackReference(value="pc")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pc_id")
    private Pc pc;

    @JsonBackReference(value="baseEntity")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_entity_id")
    private BaseEntity baseEntity;

    @JsonBackReference(value="affiliation")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "affiliation_id")
    private Affiliation affiliation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visibility_id")
    private Visibility visibility;
}
