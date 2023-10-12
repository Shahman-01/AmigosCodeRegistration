package org.example.demo.registration;

import lombok.AllArgsConstructor;
import org.example.demo.model.User;
import org.example.demo.model.UserRole;
import org.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

	private final UserService userService;
	private final EmailValidator emailValidator;

	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());

		if (!isValidEmail)
			throw new IllegalStateException("email not valid");
		return userService.signUpUser(new User(
				request.getFirstName(),
				request.getLastName(),
				request.getEmail(),
				request.getPassword(),
				UserRole.USER
		));
	}
}
