package org.shoeshop.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {    //client = tipo de objeto, long = "tipo" da chave primária

	List<Client> findByFirstName(String firstName);
	
	Client findByUsername(String username);
	
	Client findById(long id);
}
