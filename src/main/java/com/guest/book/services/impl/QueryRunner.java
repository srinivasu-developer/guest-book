package com.guest.book.services.impl;

import com.guest.book.entities.Entry;
import com.guest.book.entities.Role;
import com.guest.book.entities.User;
import com.guest.book.enums.RoleEnum;
import com.guest.book.repositories.EntryRepository;
import com.guest.book.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
public class QueryRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Override
    public void run(String... args) {
        User user1 = new User("user1@gmail.com", "$2y$12$3aa6ADToVCt9J8RYIlwQKe5yPi95kOFSl3efLERyOhcnHlfPV.zNO", "Normal", "User2");
        User user2 = new User("admin1@gmail.com", "$2y$12$QNT53hGXIc.nd9xXi/bGiujFe.Vq7pE7Sh/gqQyPIf/eNv3NGK5N.", "Admin1", "User2");
        User user3 = new User("user2@gmail.com", "test3", "Normal2", "User3");
        Role admin = new Role(RoleEnum.ADMIN);
        Role normal = new Role(RoleEnum.NORMAL);
        user1.setRoles(Set.of(normal));
        user2.setRoles(Set.of(admin));
        user3.setRoles(Set.of(normal));
        userRepository.saveAll(List.of(user1, user2, user3));

        Entry guestBook1 = new Entry("One Entry", "The first entry of guest book");
        entryRepository.saveAll(List.of(guestBook1));
    }
}
