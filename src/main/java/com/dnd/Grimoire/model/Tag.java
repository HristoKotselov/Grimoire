package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tag")
@Jacksonized
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"pcs", "baseEntities", "pictures", "visibility"})
@ToString(exclude = {"pcs", "baseEntities", "pictures", "visibility"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tag implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_seq")
    private Long tagId;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<Pc> pcs;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<BaseEntity> baseEntities;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<Picture> pictures;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visibility_id")
    private Visibility visibility;
}
