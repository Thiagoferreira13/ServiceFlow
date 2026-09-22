package com.thiagoferreira.ServiceFlow.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagoferreira.ServiceFlow.entities.User;
import com.thiagoferreira.ServiceFlow.enums.Role;



@RestController
@RequestMapping(value = "/users")
public class UserResources {

    //endpoint para acessar os usuarios
    @GetMapping 
    public ResponseEntity<User> findAll() {
        User u = new User(1L, "Maria", "maria@gemail.com", "12345", Role.ADMIN);
        return ResponseEntity.ok().body(u);
    }
}
