package org.shoeshop.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String photo;
	private String description;
	private float price;
	
	protected Product() {}
	
	public Product(String name, String photo, String description, float price) {
		this.name = name;
		this.photo = photo;
		this.description = description;
		this.price = price;
	}
	@Override
	public String toString() {
		return String.format(
				"Client[id=%d, name='%s', photo='%s', description='%s', price ='%f'",
				id, name,photo,description,price);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
	    this.name = name;
	  }

	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}



}
