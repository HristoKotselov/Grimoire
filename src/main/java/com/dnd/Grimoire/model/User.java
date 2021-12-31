package com.dnd.Grimoire.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy="user")
    private List<Pc> pcs;

    @OneToMany(mappedBy="owner")
    private List<Campaign> campaigns;
}
