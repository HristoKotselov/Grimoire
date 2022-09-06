package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pc")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"aliases", "affiliations", "pictures", "tags", "descriptions"})
@ToString(exclude = {"aliases", "affiliations", "pictures", "tags", "descriptions"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pc implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pc_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pc_seq")
    private Long pcId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id", nullable=false)
    private Account owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="campaign_id", nullable=false)
    private Campaign campaign;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "race_id")
    private Race race;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @Column(name = "hero_class")
    private String heroClass;

    @JsonManagedReference(value="pc")
    @OneToMany(mappedBy = "pc", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Alias> aliases;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "pc_affiliation",
            joinColumns = @JoinColumn(name = "pc_id"),
            inverseJoinColumns = @JoinColumn(name = "affiliation_id"))
    private List<Affiliation> affiliations;

//    @JsonManagedReference(value="pc")
    @OneToMany(mappedBy = "pc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Picture> pictures;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "pc_tag",
            joinColumns = @JoinColumn(name = "pc_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @JsonManagedReference
    @OneToMany(mappedBy = "pc", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PcDescription> descriptions;

}
