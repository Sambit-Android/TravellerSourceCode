package com.pgrs.util;

import java.util.List;

/**
 * @author user43
 *
 */
public class ModuleDetails {
	private String description;	
	private String image;	
	private String role;	
	private List<ItemDetails> items;
	
	public List<ItemDetails> getItems() {
		return items;
	}
	public void setItems(List<ItemDetails> items) {
		this.items = items;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
