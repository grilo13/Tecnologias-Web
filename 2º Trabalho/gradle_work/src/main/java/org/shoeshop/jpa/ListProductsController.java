package org.shoeshop.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ListProductsController {

	private static final Logger log = LoggerFactory.getLogger(ListProductsController.class);

    @Autowired
    private ProductRepository repository;
    
	@GetMapping("/home")
	public String listProducts(
			@RequestParam(name="name", required=true, defaultValue="ProductName") String name, 
			Model model) 
	{		
		List<Product> productsList = (List<Product>) repository.findAll();
		
		log.info("Products found with findAll():");
		log.info("-------------------------------");
		for (Product product : repository.findAll()) {
			log.info(product.toString());
		}
		log.info("");
		
		model.addAttribute("productsList", productsList);
		return "home-view";
	}
}
