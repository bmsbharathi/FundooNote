package com.bridgeit.note.validator;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bridgeit.note.model.User;

@Service
public class UserValidator implements Validator {

	public boolean supports(Class clazz) {
		return User.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "error.fullName", "name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNo", "error.mobileNo", "mobile is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.email", "email is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "password is required");

	}

}
