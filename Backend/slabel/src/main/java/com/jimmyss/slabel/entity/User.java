package com.jimmyss.slabel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Dataset> datasets;

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }

    public User(){
    }

}
