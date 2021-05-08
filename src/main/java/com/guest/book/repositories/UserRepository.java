package com.guest.book.repositories;

import com.guest.book.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * A repository class to manage {@link UserRepository}s
 *
 * @author Srinivasu Nakka
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
