package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pc")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"race", "gender", "heroClass", "pictures", "tags", "descriptions"})
@ToString(exclude = {"race", "gender", "heroClass", "pictures", "tags", "descriptions"})
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
    private Account owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="campaign_id", nullable=false)
    private Campaign campaign;

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
    @JoinTable(name = "pc_picture",
            joinColumns = @JoinColumn(name = "pc_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private List<Picture> pictures;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pc_tag",
            joinColumns = @JoinColumn(name = "pc_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @OneToMany(mappedBy = "pc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PcDescription> descriptions;

}
