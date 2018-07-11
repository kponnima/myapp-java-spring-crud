package com.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.model.News;

@Component("newsValidator")
public class NewsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return News.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.news.title");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "body", "NotEmpty.news.body");

	}

}