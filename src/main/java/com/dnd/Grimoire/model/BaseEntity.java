package com.dnd.Grimoire.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"tags", "pictures", "descriptions", "visibilities", "campaign"})
@ToString(exclude = {"tags", "pictures", "descriptions", "visibilities", "campaign"})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseEntity implements Serializable {

    @Id
    @Column(name = "base_entity_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long baseEntityId;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "base_entity_tag",
            joinColumns = @JoinColumn(name = "base_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "base_entity_picture",
            joinColumns = @JoinColumn(name = "base_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private List<Picture> pictures;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Description> descriptions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "base_entity_visibility",
            joinColumns = @JoinColumn(name = "base_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="campaign_id", nullable=false)
    private Campaign campaign;
}
