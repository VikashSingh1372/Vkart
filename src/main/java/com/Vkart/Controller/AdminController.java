package com.Vkart.Controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Vkart.DTO.ProductDTO;
import com.Vkart.Models.Category;
import com.Vkart.Models.Popular;
import com.Vkart.Models.Product;
import com.Vkart.Models.User;
import com.Vkart.Repositories.CategoryRepository;
import com.Vkart.Repositories.PopularRepo;
import com.Vkart.Repositories.ProductRepository;
import com.Vkart.Service.CategoryService;
import com.Vkart.Service.ImageService;
import com.Vkart.Service.ProductService;
import com.Vkart.Service.userService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
   CategoryService catService;	
	
	@Autowired
	ProductService productService;

	@Autowired
	CategoryRepository catRepo;
	
	@Autowired
	PopularRepo popularRepo;
	
	@Autowired
	userService userService;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	private ImageService iService;
	
	@Value("${project.image}")
	private String path;
	

	@GetMapping("/home")
	public String Home(Model model) {
		model.addAttribute("category", catService.ViewCategories());
		model.addAttribute("product", productService.GetAllProduct());
		
		return "AdminHome";
		
	}

	@GetMapping("/data")
	public String AdminData(Model model) {
		Long userLength = this.userService.userLength();
		Long categoryLength = this.catService.categoryLength();
		Long productLength = this.productService.productLength();
		model.addAttribute("category",categoryLength);
		model.addAttribute("product", productLength);
		model.addAttribute("user", userLength);

		
		System.out.println(userLength +"This thewjkfhsjkbfbjv!!!!!!!!!!");
		 
		
		return "AdminData";
		
	}
	@GetMapping("/home/category/{id}")
	public String category(@PathVariable("id") int id,Model model) {
		model.addAttribute("category", catService.ViewCategories());
        Model addAttribute = model.addAttribute("product",productService.getAllProductsByCategory(id));
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
	public String saveCategory(@RequestParam("categoryImage") MultipartFile file ,String categoryName, int categoryId)throws IOException {
		Category cat = new Category();
		cat.setCategoryId(categoryId);
		cat.setCategoryName(categoryName);

		 String fileName = this.iService.uploadImage(path ,file);
		 cat.setCategoryImage(fileName);
        catRepo.save(cat);	
        System.out.print(cat);
		
		
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
	public String UpdatingCategory(@RequestParam("categoryImage") MultipartFile file,  @PathVariable int id, Model model, @ModelAttribute("category") Category category
			) throws IOException {
		
		 Category category1= catService.getCategoryById(id);
		 category1.setCategoryName(category.getCategoryName());
		 String fileName = this.iService.uploadImage(path ,file);
	        System.out.print("this is image " +fileName);

		 category1.setCategoryImage(fileName);
		 catRepo.save(category1);
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
	public String saveProduct(@RequestParam("productImage") MultipartFile file,@ModelAttribute("product") ProductDTO productdto) throws IOException {
		
   Product product = new Product();
   Double price = productdto.getProductPrice();
   product.setProductId(productdto.getProductId());
   product.setProductDescription(productdto.getProductDescription());
   product.setProductName(productdto.getProductName());
   product.setProductPrice(productdto.getProductPrice());  
   product.setProductWeight(productdto.getProductWeight());
   product.setProductDiscount(productdto.getProductDiscount());
   
    float productDiscount = productdto.getProductDiscount();
      Double dprice = ((price*productDiscount)/100);
      float realprice = (float) (price-dprice);
   product.setRealPrice((double) realprice);
   product.setCategory(catService.getCategoryById(productdto.getCategoryid()));
   System.out.print("running sucesfully");
	 String fileName = this.iService.uploadImage(path ,file);
      product.setProductImage(fileName);
   productRepo.save(product);
   
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
		 Product product = productService.getProductById(id);
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
		Product product = productService.getProductById(id);
		ProductDTO pd = new ProductDTO();
		pd.setProductId(product.getProductId());
		pd.setProductDescription(product.getProductDescription());
		pd.setProductName(product.getProductName());
		pd.setProductPrice(product.getProductPrice());
		pd.setProductWeight(product.getProductWeight());
		pd.setCategoryid(product.getCategory().getCategoryId());
		
		pd.setImage(product.getProductImage());
	    model.addAttribute("category",catService.ViewCategories());
	    model.addAttribute("product", pd);

	   

		
                    
           
		   return "addProduct";
		 
	}
	@GetMapping("/popular")
	public String allPopularItems(Model model) {
		model.addAttribute("popular", this.popularRepo.findAll());
		
		return "popular";
		
		
	}

	@GetMapping("/popular/delete/{id}")
	public String DeletePopularItems(@PathVariable Long id) {
		this.popularRepo.deleteById(id);
		return "redirect:/admin/popular";
		 
	}
	
	@GetMapping("/allProduct/popular/{id}")
	public String PopularProduct(@PathVariable Long id) {
		  Product product = productService.getProductById(id);
		  Popular popular = new Popular();
		  popular.setName(product.getProductName());
		  popular.setDescription(product.getProductDescription());
		  popular.setImage(product.getProductImage());
		  popular.setProductId(product.getProductId());
		  popular.setDiscount(product.getProductDiscount());
		  popular.setRealPrice(product.getRealPrice());
		  popular.setPrice(product.getProductPrice());
		  this.popularRepo.save(popular);
		  
		  
		return "redirect:/admin/allProduct";
		 
	}
	
	
	
	

	

	


}
