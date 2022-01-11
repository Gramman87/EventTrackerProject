package com.skilldistillery.automatic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.automatic.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
