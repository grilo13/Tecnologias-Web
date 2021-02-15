package org.shoeshop.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

import antlr.StringUtils;

@Controller
public class NewProductController {

	private static final Logger log = LoggerFactory.getLogger(NewProductController.class);

    @Autowired
    private ProductRepository repository;
    
	@PostMapping("/new-product")   //supsotamente é PostMapping
	public String newProduct(
			@RequestParam(name="name",required=true, defaultValue="Product Name") String name, 
			@RequestParam(name="photo", required=true, defaultValue="Product Photo") String photo,
			@RequestParam(name="description", required=true, defaultValue="Product Description") String description,
			@RequestParam(name="price", required=true, defaultValue="Product Price") Float price,
			Model model)
	{
		
		//guarda cliente e coloca-o na base de dados
		repository.save(new Product(name, photo, description, price));
		
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Product aProduct : repository.findAll()) {
			log.info(aProduct.toString());
		}
		log.info("");
		
		model.addAttribute("name", name);
		model.addAttribute("photo", photo);
		model.addAttribute("description", description);
		model.addAttribute("price", price);
		
		return "new-product-view";
	}
}