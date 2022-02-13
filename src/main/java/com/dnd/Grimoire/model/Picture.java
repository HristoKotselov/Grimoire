package com.dnd.Grimoire.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "picture")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Picture {

    @Id
    @Column(name = "picture_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pictureId;

    @ManyToMany(mappedBy = "pictures", fetch = FetchType.LAZY)
    private List<Pc> pcs;

    @ManyToMany(mappedBy = "pictures", fetch = FetchType.LAZY)
    private List<BaseEntity> baseEntity;

    @Column(name = "label")
    private String label;

    @Column(name = "filepath")
    private String filepath;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "picture_visibility",
            joinColumns = @JoinColumn(name = "picture_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;
}
