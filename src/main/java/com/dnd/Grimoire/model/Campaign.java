package com.dnd.Grimoire.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "campaign")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"owner", "entities"})
@ToString(exclude = {"owner", "entities"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Campaign {

    @Id
    @Column(name = "campaign_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long campaignId;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id", nullable=false)
    private Account owner;

    @OneToMany(mappedBy="campaign", fetch = FetchType.LAZY)
    private List<Pc> pcs;

    @OneToMany(mappedBy="campaign", fetch = FetchType.LAZY)
    private List<BaseEntity> entities;
}
