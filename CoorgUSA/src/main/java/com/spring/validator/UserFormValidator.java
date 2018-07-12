package com.spring.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.model.User;
import com.spring.service.UserService;

@Component("userFormValidator")
public class UserFormValidator implements Validator {

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;

	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	private static final int MINIMUM_FIELD_LENGTH = 6;
	private static final int MAX_FIELD_LENGTH = 16;
	private static final String NAME_PATTERN = "[a-zA-Z ]{3,30}";
	private static final String USERNAME_PATTERN = "/^[a-zA-Z0-9@!]{6,12}$/";

	@Override
	public void validate(Object target, Errors errors) {

		User user = (User) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "NotEmpty.user.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "NotEmpty.user.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.user.userName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.user.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmpassword", "NotEmpty.user.confirmpassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.user.email");

		if (user.getUsername().length() < MINIMUM_FIELD_LENGTH) {
			errors.rejectValue("username", "username.min.length");
		}
		if (!validname(user.getFirstname())) {
			errors.rejectValue("firstname", "Pattern.user.firstname");
		}
		if (!validname(user.getLastname())) {
			errors.rejectValue("lastnasme", "Pattern.user.lastname");
		}
		/*if (!validusername(user.getUsername())) {
			errors.rejectValue("username", "Pattern.user.username");
		}*/
		if (user.getPassword().length() < MINIMUM_FIELD_LENGTH || user.getPassword().length() > MAX_FIELD_LENGTH) {
			errors.rejectValue("username", "password.minmax.length");
		}
		if (!user.getPassword().equals(user.getConfirmpassword())) {
			errors.rejectValue("confirmpassword", "Diff.user.confirmpassword");
		}
		if (!emailValidator.valid(user.getEmail())) {
			errors.rejectValue("email", "Pattern.user.email");
		}
	}

	public boolean validname(final String name) {
		Pattern pattern = Pattern.compile(NAME_PATTERN);
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}

	public boolean validusername(final String username) {
		Pattern pattern = Pattern.compile(USERNAME_PATTERN);
		Matcher matcher = pattern.matcher(username);
		return matcher.matches();
	}

}
