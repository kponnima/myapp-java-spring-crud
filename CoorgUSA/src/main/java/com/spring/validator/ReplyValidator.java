package com.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.model.Reply;

@Component("replyValidator")
public class ReplyValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Reply.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "body", "NotEmpty.Reply.body");

	}

}