package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pc_description")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"pc", "descriptionText", "visibility"})
@ToString(exclude = {"pc", "descriptionText", "visibility"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PcDescription implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "description_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pc_description_seq")
    private Long descriptionId;

    @JsonBackReference
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
