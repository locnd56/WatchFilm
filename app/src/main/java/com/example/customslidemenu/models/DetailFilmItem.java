package com.example.customslidemenu.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties (ignoreUnknown = true)
public class DetailFilmItem {
	
	int id;
	String title;
	String image;
	public DetailFilmItem(@JsonProperty("Id") int id, @JsonProperty("Title") String title, @JsonProperty("ImagePath") String image){
		this.id = id;
		this.title = title;
		this.image = image;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return title;
	}
	public void setName(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
