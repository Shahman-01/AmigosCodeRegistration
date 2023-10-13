package org.example.demo.registration;

import lombok.AllArgsConstructor;
import org.example.demo.registration.token.ConfirmationToken;
import org.example.demo.registration.token.ConfirmationTokenService;
import org.example.demo.user.User;
import org.example.demo.user.UserRole;
import org.example.demo.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

	private final UserService userService;
	private final EmailValidator emailValidator;
	private final ConfirmationTokenService confirmationTokenService;

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

	@Transactional
	public String confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService
				.getToken(token)
				.orElseThrow(() -> new IllegalStateException("token not found"));

		if (confirmationToken.getConfirmedAt() != null)
			throw new IllegalStateException("email already confirmed");

		LocalDateTime expiredAt = confirmationToken.getExpiredAt();

		if (expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("token expired");
		}

		confirmationTokenService.setConfirmedAt(token);
		userService.enableUser(
				confirmationToken.getUser().getEmail());

		return "confirmed";
	}
}
