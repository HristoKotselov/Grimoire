package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "rarity")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"visibilities"})
@ToString(exclude = {"visibilities"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Rarity implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "rarity_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rarity_seq")
    private Long rarityId;

    @Column(name = "rarity_type")
    @Enumerated(EnumType.STRING)
    private RarityType rarityType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rarity_visibility",
            joinColumns = @JoinColumn(name = "rarity_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;

}
