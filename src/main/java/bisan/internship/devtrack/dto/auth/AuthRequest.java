package bisan.internship.devtrack.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
public class AuthRequest extends ModelBase {

    private static final long serialVersionUID = 7151443507829405471L;

    @Email(message = "A valid email address must be provided")
    @NotNull(message = "Email must be provided")
    private String email;

    @NotNull(message = "Password must be provided")
    private String password;

    /**
     * Represents an authentication request containing the user's email and password.
     * This class is used to encapsulate the necessary data for user authentication,
     * focusing on email and password as the primary credentials.
     *
     * Fields are validated to ensure that both email and password are provided,
     * with the email field requiring a valid email format.
     */
}
