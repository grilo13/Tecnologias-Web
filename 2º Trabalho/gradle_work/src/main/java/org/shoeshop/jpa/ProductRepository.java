package org.shoeshop.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository("productRepository")
public interface ProductRepository extends CrudRepository<Product, Long> {    

	Product findById(long id);
	
	List<Product> findByNameContainsIgnoreCase(String name);
	
	List<Product> findByName(String name);
	

}
