package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "visibility")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Visibility {

    @Id
    @Column(name = "visibility_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long visibilityId;

    @Column(name = "visibility_type")
    @Enumerated(EnumType.STRING)
    private VisibilityType visibilityType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pc_id")
    private Pc owner;
}
