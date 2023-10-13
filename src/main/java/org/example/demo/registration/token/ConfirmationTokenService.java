package org.example.demo.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
	private final ConfirmationTokenRepo tokenRepo;

	public void saveToken(ConfirmationToken token) {
		tokenRepo.save(token);
	}
}
