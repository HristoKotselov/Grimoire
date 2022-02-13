package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "visibility")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"pcs", "rarities", "affiliations", "aliases", "baseEntities", "descriptions", "locations",
        "pictures", "tags", "races", "genders", "itemValues", "attunements"})
@ToString(exclude = {"pcs", "rarities", "affiliations", "aliases", "baseEntities", "descriptions", "locations",
        "pictures", "tags", "races", "genders", "itemValues", "attunements"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Visibility {

    @Id
    @Column(name = "visibility_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long visibilityId;

    @OneToOne(mappedBy = "owningVisibility")
    private Pc owner;

    @ManyToMany(mappedBy = "visibilities", fetch = FetchType.LAZY)
    private List<Pc> pcs;

    @ManyToMany(mappedBy = "visibilities", fetch = FetchType.LAZY)
    private List<Rarity> rarities;

    @ManyToMany(mappedBy = "visibilities", fetch = FetchType.LAZY)
    private List<Affiliation> affiliations;

    @ManyToMany(mappedBy = "visibilities", fetch = FetchType.LAZY)
    private List<Alias> aliases;

    @ManyToMany(mappedBy = "visibilities", fetch = FetchType.LAZY)
    private List<BaseEntity> baseEntities;

    @ManyToMany(mappedBy = "visibilities", fetch = FetchType.LAZY)
    private List<Description> descriptions;

    @ManyToMany(mappedBy = "visibilities", fetch = FetchType.LAZY)
    private List<Location> locations;

    @ManyToMany(mappedBy = "visibilities", fetch = FetchType.LAZY)
    private List<Picture> pictures;

    @ManyToMany(mappedBy = "visibilities", fetch = FetchType.LAZY)
    private List<Tag> tags;

    @ManyToMany(mappedBy = "visibilities", fetch = FetchType.LAZY)
    private List<Race> races;

    @ManyToMany(mappedBy = "visibilities", fetch = FetchType.LAZY)
    private List<Gender> genders;

    @ManyToMany(mappedBy = "visibilities", fetch = FetchType.LAZY)
    private List<ItemValue> itemValues;

    @ManyToMany(mappedBy = "visibilities", fetch = FetchType.LAZY)
    private List<Attunement> attunements;
}
