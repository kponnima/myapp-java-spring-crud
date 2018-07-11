package com.spring.model;

import org.springframework.web.multipart.MultipartFile;

public class VideoBucket {

	MultipartFile videofile;

	String description;

	public MultipartFile getVideofile() {
		return videofile;
	}

	public void setVideofile(MultipartFile videofile) {
		this.videofile = videofile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
