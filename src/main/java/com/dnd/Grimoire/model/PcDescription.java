package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "pc_description")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"pc", "descriptionText", "visibility"})
@ToString(exclude = {"pc", "descriptionText", "visibility"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PcDescription {

    @Id
    @Column(name = "description_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long descriptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pc_id")
    private Pc pc;

    @Lob
    @Column(name = "description_text")
    private String descriptionText;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visibility_id")
    private Visibility visibility;
}
