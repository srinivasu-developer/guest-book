package com.guest.book.services.impl;

import com.guest.book.services.SecurityService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void doAutoLogin(String username, String password) {

    }
}
