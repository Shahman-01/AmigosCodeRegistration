package org.example.demo.service;

import lombok.AllArgsConstructor;
import org.example.demo.model.User;
import org.example.demo.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
	private final UserRepo userRepo;
	private final BCryptPasswordEncoder passwordEncoder;
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

		// TODO: send confirmation token

		return "it works";
	}
}
