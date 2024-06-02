package it.epicode.exceptions;

import java.security.Provider;

public class InvalidLoginException extends ServiceException {
    private static final long serialVersionUID = 1L;

    /**
     * Username.
     */
    public final String username;
    /**
     * Password.
     */
    public final String password;

    public InvalidLoginException(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
