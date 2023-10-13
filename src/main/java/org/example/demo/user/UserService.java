package org.example.demo.user;

import lombok.AllArgsConstructor;
import org.example.demo.registration.token.ConfirmationToken;
import org.example.demo.registration.token.ConfirmationTokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
	private final UserRepo userRepo;
	private final BCryptPasswordEncoder passwordEncoder;
	private final ConfirmationTokenService tokenService;
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return userRepo.findByEmail(s)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	}

	public String signUpUser(User user) {
		boolean userExists = userRepo
				.findByEmail(user.getEmail())
				.isPresent();
		if (userExists)
			throw new IllegalStateException("email already taken");

		String encodedPassword = passwordEncoder.encode(user.getPassword());

		user.setPassword(encodedPassword);

		userRepo.save(user);

		String token = UUID.randomUUID().toString();

		ConfirmationToken confirmationToken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				user
		);

		tokenService.saveToken(confirmationToken);

		return token;
	}

	public int enableUser(String email) {
		return userRepo.enableUser(email);
	}
}
