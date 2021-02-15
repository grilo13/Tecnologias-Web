package org.shoeshop.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {

	@Id  /*chave primária de cliente */
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String role;
	private String password;

		
	protected Client() {}

	public Client(String firstName, String lastName, String email,String username, String role, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.role = role;
		this.password = password;
	}

	@Override
	public String toString() {
		return String.format(
				"Client[id=%d, firstName='%s', lastName='%s', email='%s', username ='%s', role='%s', password='%s']",
				id, firstName, lastName, email, username, role, password);
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}	
	
	public String getUsername() {
		return username;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getPassword() {
		return password;
	}
}
