package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"campaign", "aliases", "tags", "pictures", "descriptions", "visibilities"})
@ToString(exclude = {"campaign", "aliases", "tags", "pictures", "descriptions", "visibilities"})
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Plane.class, name = "Plane"),
        @JsonSubTypes.Type(value = Continent.class, name = "Continent"),
        @JsonSubTypes.Type(value = Region.class, name = "Region"),
        @JsonSubTypes.Type(value = Settlement.class, name = "Settlement"),
        @JsonSubTypes.Type(value = District.class, name = "District"),
        @JsonSubTypes.Type(value = Place.class, name = "Place"),
        @JsonSubTypes.Type(value = Npc.class, name = "Npc"),
        @JsonSubTypes.Type(value = Monster.class, name = "Monster"),
        @JsonSubTypes.Type(value = Item.class, name = "Item"),
        @JsonSubTypes.Type(value = Event.class, name = "Event") }
)
public abstract class BaseEntity implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "base_entity_id")
    @GeneratedValue(generator = "sequenceIdGenerator")
    @GenericGenerator(
            name = "sequenceIdGenerator",
            strategy = "sequence",
            parameters = @Parameter(
                    name = SequenceStyleGenerator.CONFIG_PREFER_SEQUENCE_PER_ENTITY,
                    value = "true"))
    private Long baseEntityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="campaign_id", nullable=false)
    private Campaign campaign;

    @Column(name = "name")
    private String name;

    @JsonManagedReference(value="baseEntity")
    @OneToMany(mappedBy = "baseEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Alias> aliases;

    @JsonManagedReference
    @OneToMany(mappedBy = "baseEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EntityDescription> descriptions;

//    @JsonManagedReference(value="baseEntity")
    @OneToMany(mappedBy = "baseEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Picture> pictures;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
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
