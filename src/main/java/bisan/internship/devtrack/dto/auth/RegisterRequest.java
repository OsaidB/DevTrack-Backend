package bisan.internship.devtrack.dto.auth;

import bisan.internship.devtrack.dto.auth.ModelBase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest extends ModelBase {

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
    @NotNull(message = "First name must be provided")
    private String firstName;

    @NotNull(message = "Last name must be provided")
    private String lastName;

    @NotNull(message = "Functional role must be provided")
    private Long functionalRoleId; // This should match the type used for functional roles

    @NotNull(message = "Team leader status must be provided")
    private Boolean isTeamLeader;

    private Boolean isAdmin;


    private String authorities; // Optional, based on your application's requirements

    /**
     * Represents a request to register a new user, including all required details for registration.
     * Fields are validated to ensure completeness and correctness for user creation.
     */
}

