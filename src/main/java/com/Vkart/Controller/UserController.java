package com.Vkart.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Vkart.Models.Address;
import com.Vkart.Models.Product;
import com.Vkart.Models.Rating;
import com.Vkart.Models.User;
import com.Vkart.Repositories.AddressRepo;
import com.Vkart.Repositories.PopularRepo;
import com.Vkart.Service.CategoryService;
import com.Vkart.Service.ImageService;
import com.Vkart.Service.ProductService;
import com.Vkart.Service.userService;
import com.Vkart.cart.Cart;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private userService userservice;

	@Autowired
	private CategoryService catservice;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private PopularRepo popularRepo;
	
	@Autowired
	private ImageService iService;
	
	
	@Autowired
	private AddressRepo aRepo;

	@GetMapping("/home")
	public String Home(Model model,Principal principal) {
		model.addAttribute("category", this.catservice.ViewCategories());
		model.addAttribute("product", productService.GetAllProduct());
		model.addAttribute("popular",this.popularRepo.findAll());
		String name = principal.getName();
		System.out.print(name);
		User user = userservice.getUserByUserName(name);
		model.addAttribute("user", user);
		model.addAttribute("popular",this.popularRepo.findAll());

		
		return "userHome";
		
	}
	@GetMapping("/home/category/{id}")
	public String category(@PathVariable("id") int id,Model model,Principal principal) {
		model.addAttribute("category", catservice.getCategoryById(id));
        Model addAttribute = model.addAttribute("product",productService.getAllProductsByCategory(id));
        System.out.print(addAttribute);
        String name = principal.getName();
		System.out.print(name);
		User user = userservice.getUserByUserName(name);
		model.addAttribute("user", user);
		return "userproducts";
	}
	
	
	@GetMapping("/home/productsById")
	public String allProduct(Model model) {
		model.addAttribute("category", catservice.ViewCategories());
		model.addAttribute("product", productService.GetAllProduct());
        
		return "userproductByCat";
	}
	@GetMapping("/home/productId/{id}")
	public String allProduvtById(@PathVariable("id") int id,Model model) {
		model.addAttribute("category", catservice.getCategoryById(id));
		model.addAttribute("category", catservice.ViewCategories());
		model.addAttribute("product", productService.GetAllProduct());
        Model addAttribute = model.addAttribute("product",productService.getAllProductsByCategory(id));
        System.out.print(addAttribute);
        
		return "UserproductByCat";
	}
	@GetMapping("/home/product/{id}")
	public String Singleproduct(@PathVariable("id") Long id,Model model) {
        Model addAttribute = model.addAttribute("product",productService.getProductById(id));
        
        
        
		return "usersingleProduct";
	}
       @GetMapping("/buy/{id}")
       public String buyingPage(Principal principal  ,Model model, @PathVariable Long id) {
    	   String name = principal.getName();
    	   System.out.print("name is thiss" + name);
    	   
    	   
    	      User user = this.userservice.getUserByUserName(name);
    	      List<Address> address = this.aRepo.findAddressByUser(user.getUserId());
    	      
    	      if(address.isEmpty()) {
    	  		return "total";

    	      }
    	      
    	        Model addAttribute = model.addAttribute("product",productService.getProductById(id));
		return "totalAmount";
    	   
       }
       
       
       @GetMapping("/checkout/{id}")
       public String checkout() {
		return "checkout";
    	   
       }
  
   	
   @GetMapping("/AddTocart/{id}")
   	public String cart(@PathVariable long id,Model model) {
   	 Cart.cart.add(productService.getProductById(id));
   	model.addAttribute("product",productService.getProductById(id));
   	
   		return "usersingleProduct";
   		
   	
   }
   @GetMapping("/cart")
   public String cart(Model model) {
   	model.addAttribute("cart",Cart.cart);
   	model.addAttribute("cartCount", Cart.cart.size());
   	model.addAttribute("TotalPrice",Cart.cart.stream().mapToDouble(Product :: getProductPrice).sum());
   	return "cart";
   	
   }
   @GetMapping("/remove/{id}")
   public String removeCartItem(@PathVariable int id,Model model) {
	   Cart.cart.clear();
   	return "cart";
   	
   }
   
   
   
       
       
   

   
}
