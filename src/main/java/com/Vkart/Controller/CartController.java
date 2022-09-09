package com.Vkart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Vkart.Models.Product;
import com.Vkart.Repositories.ProductRepository;
import com.Vkart.Service.ProductService;
import com.Vkart.cart.Cart;

@Controller
public class CartController {

	@Autowired
	ProductService productService;
	
@GetMapping("/AddTocart/{id}")
	public String cart(@PathVariable long id,Model model) {
	 Cart.cart.add(productService.getProductById(id).get());
	model.addAttribute("product",productService.getProductById(id).get());
	
		return "redirect:/cart";
		
	
}
@GetMapping("/cart")
public String cart(Model model) {
	model.addAttribute("cart",Cart.cart);
	model.addAttribute("cartCount", Cart.cart.size());
	model.addAttribute("TotalPrice",Cart.cart.stream().mapToDouble(Product :: getProductPrice).sum());
	return "cart";
	
}
@GetMapping("/remove/{id}")
public String removeCartItem(@PathVariable long id,Model model) {
 Cart.cart.remove(id);
	return "redirect:/cart";
	

}

}
