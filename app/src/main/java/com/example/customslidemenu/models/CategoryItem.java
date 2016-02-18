package com.example.customslidemenu.models;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryItem {

	List<DetailFilmItem> arr;

	public CategoryItem(@JsonProperty("data") List<DetailFilmItem> arr) {
		this.arr = arr;
	}

	public List<DetailFilmItem> getArr() {
		return arr;
	}

	public void setArr(List<DetailFilmItem> arr) {
		this.arr = arr;
	}
	
}
