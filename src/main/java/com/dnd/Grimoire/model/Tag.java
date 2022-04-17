package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "tag")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"name", "pcs", "baseEntities", "visibility"})
@ToString(exclude = {"name", "pcs", "baseEntities", "visibility"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tag {

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tagId;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<Pc> pcs;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<BaseEntity> baseEntities;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<Picture> pictures;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visibility_id")
    private Visibility visibility;
}
