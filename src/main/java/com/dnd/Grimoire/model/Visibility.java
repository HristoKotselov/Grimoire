package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "visibility")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Visibility implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "visibility_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visibility_seq")
    private Long visibilityId;

    @Column(name = "visibility_type")
    @Enumerated(EnumType.STRING)
    private VisibilityType visibilityType;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pc_id")
    private Pc owner;
}
