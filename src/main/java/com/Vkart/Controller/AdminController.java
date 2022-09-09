package com.Vkart.Controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Vkart.DTO.ProductDTO;
import com.Vkart.Models.Category;
import com.Vkart.Models.Product;
import com.Vkart.Service.CategoryService;
import com.Vkart.Service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
   CategoryService catService;	
	@Autowired
	ProductService productService;
	
	

	@GetMapping("/home")
	public String Home(Model model) {
		model.addAttribute("category", catService.ViewCategories());
		model.addAttribute("product", productService.GetAllProduct());
		
		return "AdminHome";
		
	}
	@GetMapping("/home/category/{id}")
	public String category(@PathVariable("id") int id,Model model) {
		model.addAttribute("category", catService.ViewCategories());
        Model addAttribute = model.addAttribute("product",productService.getAllProductsBycategory(id));
        System.out.print(addAttribute);
        
		return "AdminHome";
	}
	//ADDING CATEGORY
	
	@GetMapping("/addCategory")
	public String AddingCategory(Model model) {
		model.addAttribute("category",new Category());
		return "addCategory";
		
	}
	@PostMapping("/SavingCategory")
	public String saveCategory(@ModelAttribute("category") Category category) {
        catService.AddCategory(category);	
        System.out.print(category);
		
		
		return "redirect:/admin/allCategories";
		
	}
	
	@GetMapping("/allCategories")
	public String AllCategory(Model model) {
		model.addAttribute("category",catService.ViewCategories());
		
	catService.ViewCategories();
		return "allCategories";
		
	}
	//delete handler for category
	@GetMapping("/allCategories/delete/{id}")
	public String DeleteCategory(@PathVariable int id) {
		catService.deleteCategory(id);
		
		return "redirect:/admin/allCategories"; 
		
	}
	//update handler for category
	@GetMapping("/allCategories/update/{id}")
	public String UpdateCategory(@PathVariable int id,Model model) {
		model.addAttribute("category",catService.getCategoryById(id));	
		 
		
		return "updateCategory";
		
	}
	@PostMapping("/UpdatingCategory/{id}")
	public String UpdatingCategory(@PathVariable int id,Model model, @ModelAttribute ("category") Category category) {
		
		 Category category1= catService.getCategoryById(id);
		 category1.setCategoryId(category.getCategoryId());
		 category1.setCategoryName(category.getCategoryName());
		 catService.AddCategory(category1);
		return "redirect:/admin/allCategories";
		
	}
	
	////////product handlers
	
	
	
	@GetMapping("/addProduct")
	public String AddingProducty(Model  model) {
		model.addAttribute("product", new ProductDTO());
		model.addAttribute("category", catService.ViewCategories());
		return "addProduct";
		
	}
	@PostMapping("/SavingProduct")
	public String saveProduct(@ModelAttribute("product") ProductDTO productdto) {
		
   Product product = new Product();
   product.setProductId(productdto.getProductId());
   product.setProductDescription(productdto.getProductDescription());
   product.setProductName(productdto.getProductName());
   product.setProductPrice(productdto.getProductPrice());  
   product.setProductWeight(productdto.getProductWeight());
   product.setCategory(catService.getCategoryById(productdto.getCategoryid()));
productService.saveProducts(product);
		return "addProduct";
		
	}
	
	
	@GetMapping("/allProduct")
	public String AllProducts(Model model) {
   model.addAttribute("product",productService.GetAllProduct());
		return "allProduct";	
		

	}
	//single product
	@GetMapping("/home/product/{id}")
	public String product(@PathVariable("id") long id,Model model) {
		 Product product = productService.getProductById(id).get();
				model.addAttribute("product",product);
		
        
		return "singleProduct";
	}

	@GetMapping("/allProduct/delete/{id}")
	public String DeleteProduct(@PathVariable Long id) {
		productService.removeById(id);
		return "redirect:/admin/allProduct";
		 
	}
	
	
	@GetMapping("/allProduct/update/{id}")
	public String updatingProduct(@PathVariable Long id ,Model model){
		Product product = productService.getProductById(id).get();
		ProductDTO pd = new ProductDTO();
		pd.setProductId(product.getProductId());
		pd.setProductDescription(product.getProductDescription());
		pd.setProductName(product.getProductName());
		pd.setProductPrice(product.getProductPrice());
		pd.setProductWeight(product.getProductWeight());
		pd.setCategoryid(product.getCategory().getCategoryId());
	    model.addAttribute("category",catService.ViewCategories());
	    model.addAttribute("product", pd);

		
                    
           
		   return "addProduct";
		 
	}


}
