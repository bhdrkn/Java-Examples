package com.bahadirakin.entity;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "T_USER")
@Access(AccessType.FIELD)
@Audited
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "C_USERNAME", unique = true)
    private String username;

    @Column(name = "C_PASSWORD")
    private String password;

    @Column(name = "C_EMAIL")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
