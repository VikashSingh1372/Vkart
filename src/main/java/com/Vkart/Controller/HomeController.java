package com.Vkart.Controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Vkart.DTO.UserDTO;
import com.Vkart.Models.Message;
import com.Vkart.Models.Product;
import com.Vkart.Models.User;
import com.Vkart.Repositories.PopularRepo;
import com.Vkart.Repositories.ProductRepository;
import com.Vkart.Repositories.userRepository;
import com.Vkart.Service.CategoryService;
import com.Vkart.Service.ImageService;
import com.Vkart.Service.ProductService;
import com.Vkart.Service.userService;

@Controller
public class HomeController {
	
	
	@Autowired
	CategoryService catservice;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository pRepo;
	
	@Autowired
	userService userservice;
	@Autowired
	PopularRepo popularRepo;
	
	@Autowired
	private userRepository userRepo;
	
	@Autowired
	private userService uservice;
	
	@Autowired
	private ImageService iService;
	
	@Value("${project.image}")
	private String path;
	
	@GetMapping("/home")
	public String Home(Model model,Principal principal) {
		model.addAttribute("category", catservice.ViewCategories());
		model.addAttribute("product", productService.GetAllProduct());
		model.addAttribute("popular",this.popularRepo.findAll());
		model.addAttribute("user", userRepo.findAll());

		
		return "home";
		
	}
	@GetMapping("/home/category/{id}")
	public String category(@PathVariable("id") int id,Model model) {
		model.addAttribute("category", catservice.getCategoryById(id));
        Model addAttribute = model.addAttribute("product",productService.getAllProductsByCategory(id));
        System.out.print(addAttribute);
        
		return "products";
	}
	
	@GetMapping("/home/productsById")
	public String allProduct(Model model) {
		model.addAttribute("category", catservice.ViewCategories());
		model.addAttribute("product", productService.GetAllProduct());
        
		return "productByCat";
	}
	@GetMapping("/home/productId/{id}")
	public String allProduvtById(@PathVariable("id") int id,Model model) {
		model.addAttribute("category", catservice.getCategoryById(id));
		model.addAttribute("category", catservice.ViewCategories());
		model.addAttribute("product", productService.GetAllProduct());
        Model addAttribute = model.addAttribute("product",productService.getAllProductsByCategory(id));
        System.out.print(addAttribute);
        
		return "productByCat";
	}
	@GetMapping("/home/product/{id}")
	public String product(@PathVariable("id") Long id,Model model) {
        Model addAttribute = model.addAttribute("product",productService.getProductById(id));
        
		return "singleProduct";
	}
	
	@GetMapping("/register")
	public String signin(Model model) {
		model.addAttribute("user",new UserDTO());
	
		return "signup";
		
	}
	
	@PostMapping("/do_register")
	public String signingin(@ModelAttribute("user") UserDTO userdto, HttpSession session) throws IOException {
	
		if(userdto.getUserName() == "") {
			session.setAttribute("message", new Message("username required!!", "success"));
			return "signup" ;

		}
		if(userdto.getUserEmail() == "") {
			session.setAttribute("message", new Message(" email required!!", "success"));
			return "signup" ;

		}	if(userdto.getUserPassword() == "") {
			session.setAttribute("message", new Message("password required!!", "success"));
			return "signup" ;

		}
		if(userdto.getUserGender() == null) {
			session.setAttribute("message", new Message("gender required!!", "success"));
			return "signup" ;

		}
		else {
			userservice.SaveUser(userdto);
			session.setAttribute("message", new Message("Succesfully Registered Login Now !!" , "success"));
		
		}
	
		
		
		return "signup";
		
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
		
	}

	@GetMapping("/search/{query}")
	public ResponseEntity<?> search( @PathVariable("query") String query){
		
		List<Product> q = this.pRepo.findByProductNameContaining(query);
		
		return ResponseEntity.ok(q);
		
	}
	
}
