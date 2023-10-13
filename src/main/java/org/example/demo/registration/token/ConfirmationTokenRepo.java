package org.example.demo.registration.token;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepo extends CrudRepository<ConfirmationToken, Long> {
	Optional<ConfirmationToken> findByToken(String token);
}
