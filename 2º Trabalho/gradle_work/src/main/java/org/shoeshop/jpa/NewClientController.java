package org.shoeshop.jpa;

import java.util.List;

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

@Controller
public class NewClientController {

	private static final Logger log = LoggerFactory.getLogger(NewClientController.class);

    @Autowired
    private ClientRepository repository;
    
	@PostMapping("/new-client")   //supsotamente é PostMapping
	public String newClient(
			@RequestParam(name="name",required=true, defaultValue="User Name") String name, 
			@RequestParam(name="lastname", required=true, defaultValue="User Lastname") String lastname,
			@RequestParam(name="email", required=true, defaultValue="User Email") String email,
			@RequestParam(name="username", required=true, defaultValue="Username") String username,
			@RequestParam(name="role", required=true, defaultValue="User Role") String role,
			@RequestParam(name="password", required=true, defaultValue="User Password") String password,
			Model model)
	{
		
		/*Client client = new Client(name)
		 *repository.save(new Client(name)), outra maneira de representar 
		*/
		
		for(Client aClient :repository.findAll()) {
			
			if(aClient.getUsername().equals(username) && !(aClient.getEmail().equals(email))){
				log.info("User already exists..");
				
				model.addAttribute("name", name);
				return "new-username-already-exists-view";
			}
			
			if(aClient.getEmail().equals(email) && !(aClient.getUsername().equals(username))){
				log.info("Email already being used..");
				
				model.addAttribute("email", email);
				return "new-email-already-exists-view";
			}
			
			if(aClient.getEmail().equals(email) && aClient.getUsername().equals(username)){
				log.info("Email and username already being used..");
				
				model.addAttribute("name", name);
				model.addAttribute("email", email);
				return "new-username-and-email-already-exists-view";
			}
		}
		
		String encodedPassword = new BCryptPasswordEncoder().encode("password"); 
		
		//guarda cliente e coloca-o na base de dados
		repository.save(new Client(name, lastname, email,username, role, encodedPassword));
		
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Client aClient : repository.findAll()) {
			log.info(aClient.toString());
		}
		log.info("");
		
		model.addAttribute("name", name);
		model.addAttribute("lastname",lastname);
		model.addAttribute("email", email);
		model.addAttribute("username", username);
		model.addAttribute("role", role);
		model.addAttribute("password", encodedPassword);
		
		return "new-client-view";
	}
}
