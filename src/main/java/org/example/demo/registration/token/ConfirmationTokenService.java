package org.example.demo.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
	private final ConfirmationTokenRepo tokenRepo;

	public void saveToken(ConfirmationToken token) {
		tokenRepo.save(token);
	}

	public Optional<ConfirmationToken> getToken(String token) {
		return tokenRepo.findByToken(token);
	}

	public int setConfirmedAt(String token) {
		return tokenRepo.updateConfirmedAt(
				token, LocalDateTime.now());
	}
}
