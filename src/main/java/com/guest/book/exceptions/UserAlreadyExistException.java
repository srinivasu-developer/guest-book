package com.guest.book.exceptions;

/**
 * An exception class to manage {@link UserAlreadyExistException}s
 *
 * @author Srinivasu Nakka
 */
public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String message) {
        super(message);
    }


}
