package com.Vkart.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Vkart.Models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("from Product as d where d.category.categoryId=:categoryId")
	List<Product> findAllBycategory(@Param("categoryId") int categoryId);

}
