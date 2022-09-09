package com.Vkart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Vkart.DTO.UserDTO;
import com.Vkart.Models.User;
import com.Vkart.Service.CategoryService;
import com.Vkart.Service.ProductService;
import com.Vkart.Service.userService;

@Controller
public class HomeController {
	
	
	@Autowired
	CategoryService catservice;
	@Autowired
	ProductService productService;
	
	@Autowired
	userService userservice;
	
	@GetMapping("/home")
	public String Home(Model model) {
		model.addAttribute("category", catservice.ViewCategories());
		model.addAttribute("product", productService.GetAllProduct());
		
		return "home";
		
	}
	@GetMapping("/home/category/{id}")
	public String category(@PathVariable("id") int id,Model model) {
		model.addAttribute("category", catservice.ViewCategories());
        Model addAttribute = model.addAttribute("product",productService.getAllProductsBycategory(id));
        System.out.print(addAttribute);
        
		return "home";
	}
	@GetMapping("/home/product/{id}")
	public String product(@PathVariable("id") int id,Model model) {
        Model addAttribute = model.addAttribute("product",productService.getAllProductsBycategory(id));
        System.out.print(addAttribute);
        
		return "singleProduct";
	}
	
	@GetMapping("/register")
	public String signin(Model model) {
		model.addAttribute("user",new UserDTO());
	
		return "signup";
		
	}
	
	@PostMapping("/do_register")
	public String signingin(@ModelAttribute("user") UserDTO userdto) {
		userservice.SaveUser(userdto);
		
		
		return "signup";
		
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
		
	}

}
