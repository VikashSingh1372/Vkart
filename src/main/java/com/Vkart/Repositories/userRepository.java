package com.Vkart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Vkart.Models.User;

public interface userRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.userEmail =:userEmail")
	public User getuserByUserName(@Param("userEmail") String email);
	

}
