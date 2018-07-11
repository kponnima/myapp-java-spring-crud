package com.spring.model;

import org.springframework.web.multipart.MultipartFile;

public class PhotoBucket {

	MultipartFile photofile;

	String description;

	public MultipartFile getPhotofile() {
		return photofile;
	}

	public void setPhotofile(MultipartFile photofile) {
		this.photofile = photofile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
