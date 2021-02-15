package org.shoeshop.jpa;

import org.shoeshop.jpa.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class SecuringWebApplication {
	
	@Autowired
	private ClientRepository repository;
	private ProductRepository productRepository;
	
	 private static final Logger log = LoggerFactory.getLogger(SecuringWebApplication.class);


	public static void main(String[] args) throws Throwable {
		SpringApplication.run(SecuringWebApplication.class, args);
	}
	

	  @Bean
	  public CommandLineRunner demo(ClientRepository repository, ProductRepository productRepository) {
	    return (args) -> {
	      // save a few customers
	      repository.save(new Client("Pedro", "Grilo","pmsgrilo@hotmail.com","pgrilo00","ROLE_ADMIN","pedrogrilo13"));
	      repository.save(new Client("João", "Santos","joaos92@hotmail.com","joao92","ROLE_USER","joaozinho92"));
	      
	      productRepository.save(new Product("Stussy Tee","stussy tee photo","Simple oversized tee, ideal for all the outfits",65));
	      productRepository.save(new Product("Carhartt Jacket","carhartt jacket photo","Green Jacket for winter times",120));
	      productRepository.save(new Product("Vans Old Skool","photo vans","The Old Skool, Vans classic skate shoe ded tongue and lining and Vans signature Waffle Outsole.",70));
	    };
	  }	
}