package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "picture")
@Jacksonized
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"filepath", "pc", "baseEntity", "tags", "visibilities"})
@ToString(exclude = {"filepath", "pc", "baseEntity", "tags", "visibilities"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Picture implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "picture_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "picture_seq")
    private Long pictureId;

    @Column(name = "label")
    private String label;

    @Column(name = "filepath")
    private String filepath;

//    @JsonBackReference(value="baseEntity")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_entity_id")
    private BaseEntity baseEntity;

//    @JsonBackReference(value="pc")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pc_id")
    private Pc pc;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "picture_tag",
            joinColumns = @JoinColumn(name = "picture_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "picture_visibility",
            joinColumns = @JoinColumn(name = "picture_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;
}
