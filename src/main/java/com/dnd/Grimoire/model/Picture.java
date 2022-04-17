package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "picture")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"filepath", "pcs", "baseEntity", "tags", "visibilities"})
@ToString(exclude = {"filepath", "pcs", "baseEntity", "tags", "visibilities"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Picture {

    @Id
    @Column(name = "picture_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pictureId;

    @Column(name = "label")
    private String label;

    @Column(name = "filepath")
    private String filepath;

    @ManyToMany(mappedBy = "pictures", fetch = FetchType.LAZY)
    private List<Pc> pcs;

    @ManyToMany(mappedBy = "pictures", fetch = FetchType.LAZY)
    private List<BaseEntity> baseEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "picture_tag",
            joinColumns = @JoinColumn(name = "picture_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "picture_visibility",
            joinColumns = @JoinColumn(name = "picture_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;
}
