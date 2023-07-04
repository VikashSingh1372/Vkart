package com.Vkart.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vkart.Models.Product;
import com.Vkart.Repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepo;
	
	public void saveProducts(Product product) {
		productRepo.save(product);
		
	}
	public List<Product> GetAllProduct(){
		return productRepo.findAll();
		
	}
	public void removeById(Long id) {
		productRepo.deleteById(id);
		
	}
	public Product getProductById(Long id){
		
		return 		productRepo.findById(id).get();
		
	}
	public List<Product> getAllProductsByCategory(int id)
	{
		return productRepo.findAllByCategory(id);
		
	
	}
	public Long productLength() {
		long count = this.productRepo.count();
		return count;
		
	}
	
}
