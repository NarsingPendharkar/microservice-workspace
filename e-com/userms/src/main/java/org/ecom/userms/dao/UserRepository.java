/*
 * 
 */
package org.ecom.userms.dao;

import java.util.Optional;

import org.ecom.userms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	public boolean existsByEmail(String email);
}
