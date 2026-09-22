package com.thiagoferreira.ServiceFlow.entities;

import java.io.Serializable;
import java.time.Instant;

import com.thiagoferreira.ServiceFlow.enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
 

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    @Enumerated(EnumType.STRING)
    private Role role;
    

    public User(Long id, String name, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = true;
        this.createdAt = Instant.now();
        this.updatedAt = createdAt;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    /*
    nao sei se vai ser necessario 
    public void onCreate() {
        this.createdAt = Instant.now();
    }
    */

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    
}
