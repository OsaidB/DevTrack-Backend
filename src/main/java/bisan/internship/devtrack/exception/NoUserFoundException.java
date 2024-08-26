package bisan.internship.devtrack.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class NoUserFoundException extends UsernameNotFoundException {

    private static final long serialVersionUID = -3284993425429534536L;// Ensures that the class is compatible during serialization

    public NoUserFoundException(String username) {
        // Calls the parent constructor (UsernameNotFoundException) with a custom error message
        super(String.format("No user found with username '%s'.", username));
    }
}
