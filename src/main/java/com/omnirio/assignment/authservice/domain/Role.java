package com.omnirio.assignment.authservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class Role {

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
	private String roleId;
	@Column(unique = true)
	private String roleName;
	@Column(unique = true)
	private String roleCode;
	private RoleType roleType;

}