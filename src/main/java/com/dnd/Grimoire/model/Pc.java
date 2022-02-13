package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pc")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"race", "gender", "heroClass", "tags", "pictures"})
@ToString(exclude = {"race", "gender", "heroClass", "tags", "pictures"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pc {

    @Id
    @Column(name = "pc_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pcId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id", nullable=false)
    private Account account;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id")
    private Race race;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @Column(name = "hero_class")
    private String heroClass;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pc_tag",
            joinColumns = @JoinColumn(name = "pc_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "base_entity_picture",
            joinColumns = @JoinColumn(name = "base_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private List<Picture> pictures;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Description> descriptions;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visibility_id")
    private Visibility owningVisibility;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pc_visibility",
            joinColumns = @JoinColumn(name = "pc_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="campaign_id", nullable=false)
    private Campaign campaign;

}
