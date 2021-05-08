package com.guest.book.repositories;

import com.guest.book.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

/**
 * A repository class to manage {@link EntryRepository}s
 *
 * @author Srinivasu Nakka
 */
@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    Streamable<Entry> findByName(String name);

}
