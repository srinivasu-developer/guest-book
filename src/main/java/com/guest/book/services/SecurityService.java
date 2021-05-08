package com.guest.book.services;

public interface SecurityService {

    boolean isAuthenticated();

    void doAutoLogin(String username, String password);
}
