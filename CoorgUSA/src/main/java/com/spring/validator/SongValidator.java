package com.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.model.SongBucket;

@Component("songfileValidator")
public class SongValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SongBucket.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		SongBucket songfile = (SongBucket) obj;

		if (songfile.getSongfile() != null) {
			if (songfile.getSongfile().getSize() == 0) {
				errors.rejectValue("songfile", "valid.songfile");
			}
			if (!(songfile.getSongfile().getContentType().toLowerCase().equals("audio/mp3"))) {
				errors.rejectValue("songfile", "valid.songtype");
			}
		}
	}
}