package com.guest.book.services.impl;

import com.guest.book.entities.User;
import com.guest.book.exceptions.UserAlreadyExistException;
import com.guest.book.repositories.UserRepository;
import com.guest.book.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerNewUser(User user) throws UserAlreadyExistException {
        if (isExistingUser(user.getEmail())) {
            throw new UserAlreadyExistException(user.getEmail());
        }
        return null;
    }

    private boolean isExistingUser(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
