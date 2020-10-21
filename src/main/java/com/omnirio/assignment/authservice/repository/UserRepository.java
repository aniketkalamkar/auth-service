package com.omnirio.assignment.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omnirio.assignment.authservice.domain.User;

public interface UserRepository extends JpaRepository<User, String>{

	User findByUserName(String username);

}
