package com.spring.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.model.Event;

@Component("eventValidator")
public class EventValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Event.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Event event = (Event) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.event.title");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotEmpty.event.date");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "NotEmpty.event.location");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Date evendate = event.getDate();

		try {
			Date date1 = sdf.parse(sdf.format(date));
			Date date2 = sdf.parse(sdf.format(evendate));

			if (date2.compareTo(date1) < 0) {
				errors.rejectValue("date", "date.event.past");
			}
		} catch (ParseException e) {

		}
	}

}