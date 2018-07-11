package com.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.model.PhotoBucket;

@Component("photofileValidator")
public class PhotoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PhotoBucket.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		PhotoBucket photofile = (PhotoBucket) obj;

		if (photofile.getPhotofile() != null) {
			if (photofile.getPhotofile().getSize() == 0) {
				errors.rejectValue("photofile", "valid.photofile");
			}
			if (!(photofile.getPhotofile().getContentType().toLowerCase().equals("image/jpg")
					|| photofile.getPhotofile().getContentType().toLowerCase().equals("image/jpeg")
					|| photofile.getPhotofile().getContentType().toLowerCase().equals("image/png"))) {
				errors.rejectValue("photofile", "valid.phototype");
			}
		}
	}

}