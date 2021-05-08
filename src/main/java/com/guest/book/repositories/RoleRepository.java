package com.guest.book.repositories;

import com.guest.book.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository class to manage {@link RoleRepository}s
 *
 * @author Srinivasu Nakka
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
