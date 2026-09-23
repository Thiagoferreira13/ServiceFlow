package com.thiagoferreira.ServiceFlow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiagoferreira.ServiceFlow.entities.User;

public interface  UserRepository extends JpaRepository<User, Long>{
 
}
