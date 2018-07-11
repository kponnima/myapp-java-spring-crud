package com.spring.model;

import org.springframework.web.multipart.MultipartFile;

public class SongBucket {

	MultipartFile songfile;

	String description;

	public MultipartFile getSongfile() {
		return songfile;
	}

	public void setSongfile(MultipartFile songfile) {
		this.songfile = songfile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
