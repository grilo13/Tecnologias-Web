package org.shoeshop.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilteredItemsController {
	
	private static final Logger log = LoggerFactory.getLogger(ListProductsController.class);

    @Autowired
    private ProductRepository repository;
    
	@PostMapping("/list-products-filtered")
	public String listProductsFiltered(
			@RequestParam(name="name", required=true, defaultValue="ProductName") String name, 
			Model model) 
	{		
		List<Product> productsList = (List<Product>) repository.findByNameContainsIgnoreCase(name); //procura pela string, se existe algo que a contenha sem ligar para maiusculas ou minusculas
		
		log.info(name);
		log.info(productsList.toString());
		
		
		log.info("Products filtered found with findAll():");
		log.info("-------------------------------");
		for (Product product : repository.findByNameContainsIgnoreCase(name)) {
			log.info(product.toString());
		}
		log.info("");
		
		model.addAttribute("productsList", productsList);
		return "list-products-filtered-view";
	}

}
