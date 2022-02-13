package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "tag")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"pcs", "baseEntities"})
@ToString(exclude = {"pcs", "baseEntities"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tag {

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tagId;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<Pc> pcs;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<BaseEntity> baseEntities;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tag_visibility",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;
}
