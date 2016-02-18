package com.example.customslidemenu.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WatchFilmItem {
	int id;
	String title;
	String image;
	String filePathMovie;
	String desc;

	public WatchFilmItem(@JsonProperty("Id") int id,
			@JsonProperty("Title") String title,
			@JsonProperty("ImagePath") String image,
			@JsonProperty("FilePathMovie1") String filePathMovie,
			@JsonProperty("Description") String desc) {
		this.id = id;
		this.title = title;
		this.image = image;
		this.filePathMovie = filePathMovie;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFilePathMovie() {
		return filePathMovie;
	}

	public void setFilePathMovie(String filePathMovie) {
		this.filePathMovie = filePathMovie;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
