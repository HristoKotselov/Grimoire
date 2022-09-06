package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "gender")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"visibilities"})
@ToString(exclude = {"visibilities"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Gender implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "gender_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gender_seq")
    private Long genderId;

    @Column(name = "gender_type")
    @Enumerated(EnumType.STRING)
    private GenderType genderType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "gender_visibility",
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;
}
