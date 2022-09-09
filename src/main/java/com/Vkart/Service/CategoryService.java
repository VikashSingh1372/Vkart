package com.Vkart.Service;

import com.Vkart.Models.Category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vkart.Repositories.CategoryRepository;


@Service
public class CategoryService {
	
	@Autowired
 CategoryRepository catRepo;
	
	
	public List<Category> ViewCategories(){
		return catRepo.findAll();
		
	}
    public void deleteCategory(int id) {
    	catRepo.deleteById(id);
    	
    }
    public Category getCategoryById(int id) {
    	
		return catRepo.getById(id);
    	
    }
	

	public void AddCategory(Category category) {
		catRepo.save(category);

		
	}

}
