package com.mfirmanakbar.dans.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;

    public User() {
    }

    @Builder
    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
