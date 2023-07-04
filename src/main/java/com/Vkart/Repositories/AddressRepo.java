package com.Vkart.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Vkart.Models.Address;
import com.Vkart.Models.Product;

public interface AddressRepo  extends JpaRepository<Address, Integer> {
	
	@Query("from Address as d where d.user.userId=:Id")
	List<Address> findAddressByUser(@Param("Id") int Id);
	

}
