package org.example.demo.repo;

import org.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepo extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);
}