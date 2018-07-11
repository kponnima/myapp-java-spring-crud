package com.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.model.VideoBucket;

@Component("videofileValidator")
public class VideoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return VideoBucket.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		VideoBucket videofile = (VideoBucket) obj;

		if (videofile.getVideofile() != null) {
			if (videofile.getVideofile().getSize() == 0) {
				errors.rejectValue("videofile", "valid.videofile");
			}
			if (!(videofile.getVideofile().getContentType().toLowerCase().equals("video/mp4"))) {
				errors.rejectValue("videofile", "valid.videotype");
			}
		}
	}

}