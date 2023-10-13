package org.example.demo.user;

import org.example.demo.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepo extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);

	@Transactional
	@Modifying
	@Query("UPDATE User u " + "SET u.enabled = TRUE WHERE u.email = ?1")
	int enableUser(String email);
}
