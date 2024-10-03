package com.ReacconMind.ReacconMind.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ReacconMind.ReacconMind.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
