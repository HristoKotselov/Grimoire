package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "affiliation")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"visibility", "npcs", "aliases"})
@ToString(exclude = {"visibility", "npcs", "aliases"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Affiliation implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "affiliation_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "affiliation_seq")
    private Long affiliationId;

    @Column(name = "name")
    private String name;

    @JsonManagedReference(value="affiliation")
    @OneToMany(mappedBy = "affiliation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Alias> aliases;

    @JsonIgnore
    @ManyToMany(mappedBy = "affiliations", fetch = FetchType.LAZY)
    private List<Pc> pcs;

    @JsonIgnore
    @ManyToMany(mappedBy = "affiliations", fetch = FetchType.LAZY)
    private List<Npc> npcs;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visibility_id")
    private Visibility visibility;
}
