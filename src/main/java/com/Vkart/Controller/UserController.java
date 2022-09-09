package com.Vkart.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Vkart.Models.User;
import com.Vkart.Service.CategoryService;
import com.Vkart.Service.ProductService;
import com.Vkart.Service.userService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private userService userservice;

	@Autowired
	private CategoryService catservice;

	@Autowired
	private ProductService productservice;

	@GetMapping("/home")
	public String Home(Model model,Principal principal) {
		model.addAttribute("category", catservice.ViewCategories());
		model.addAttribute("product", productservice.GetAllProduct());
		String name = principal.getName();
		System.out.print(name);
		User user = userservice.getUserByUserName(name);
		model.addAttribute("user", user);
		
		return "userHome";
		
	}
	@GetMapping("/home/category/{id}")
	public String category(@PathVariable("id") int id,Model model,Principal principal) {
		model.addAttribute("category", catservice.ViewCategories());
        Model addAttribute = model.addAttribute("product",productservice.getAllProductsBycategory(id));
        System.out.print(addAttribute);
        String name = principal.getName();
		System.out.print(name);
		User user = userservice.getUserByUserName(name);
		model.addAttribute("user", user);
		return "userHome";
	}

}
