package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "campaign")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"owner", "pcs", "entities"})
@ToString(exclude = {"owner", "pcs", "entities"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Campaign implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "campaign_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "campaign_seq")
    private Long campaignId;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id", nullable=false)
    private Account owner;

    @JsonIgnore
    @OneToMany(mappedBy="campaign", fetch = FetchType.LAZY)
    private List<Pc> pcs;

    @JsonIgnore
    @OneToMany(mappedBy="campaign", fetch = FetchType.LAZY)
    private List<BaseEntity> entities;
}
