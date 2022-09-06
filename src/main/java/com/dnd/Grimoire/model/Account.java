package com.dnd.Grimoire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "account")
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"password", "pcs", "campaigns"})
@ToString(exclude = {"password", "pcs", "campaigns"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    private Long accountId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pc> pcs;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Campaign> campaigns;
}
