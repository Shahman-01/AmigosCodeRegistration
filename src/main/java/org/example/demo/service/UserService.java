package org.example.demo.service;

import lombok.AllArgsConstructor;
import org.example.demo.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
	private final UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return userRepo.findByEmail(s)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	}
}
