package com.dnd.Grimoire.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"campaign", "tags", "pictures", "descriptions", "visibilities"})
@ToString(exclude = {"campaign", "tags", "pictures", "descriptions", "visibilities"})
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {

    @Id
    @Column(name = "base_entity_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long baseEntityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="campaign_id", nullable=false)
    private Campaign campaign;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "baseEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Alias> aliases;

    @OneToMany(mappedBy = "baseEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EntityDescription> descriptions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "base_entity_picture",
            joinColumns = @JoinColumn(name = "base_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private List<Picture> pictures;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "base_entity_tag",
            joinColumns = @JoinColumn(name = "base_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "base_entity_visibility",
            joinColumns = @JoinColumn(name = "base_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;

}
