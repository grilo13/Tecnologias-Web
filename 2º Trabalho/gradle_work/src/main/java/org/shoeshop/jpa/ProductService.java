package org.shoeshop.jpa;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.util.StringUtils;

@Service
public class ProductService {
	
	//public Iterable<Product> findAll();
	
	//public void delete(long id);
	
	 private ProductRepository productRepository;

	    @Autowired
	    public ProductService(ProductRepository productRepository) {
	        this.productRepository = productRepository;
	    }
	    
	    public void  saveProductToDB(MultipartFile file,String name,String description ,float  price)
		{
			Product p = new Product();
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			if(fileName.contains(".."))
			{
				System.out.println("not a a valid file");
			}
			try {
				p.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			p.setDescription(description);
			
	        p.setName(name);
	        p.setPrice(price);
	        
	        productRepository.save(p);
		}

	    public Product get(Long id){
	        return productRepository.findById(id).get();
	    }

	    public void delete(Long id){
	        productRepository.deleteById(id);
	    }

		public Iterable<Product> findByNameContainsIgnoreCase(String name) {
			return productRepository.findByNameContainsIgnoreCase(name);
		}
		
}
