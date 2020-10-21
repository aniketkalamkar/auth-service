package com.omnirio.assignment.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omnirio.assignment.authservice.domain.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

}
