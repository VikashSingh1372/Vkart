package com.Vkart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Vkart.Models.Roles;

public interface roleRepository extends JpaRepository<Roles, Integer> {
	
	@Query("select r from Roles r where r.roleId =:roleId")
	public Roles getRoleByRoleId(@Param("roleId") int roleId);
	

}
