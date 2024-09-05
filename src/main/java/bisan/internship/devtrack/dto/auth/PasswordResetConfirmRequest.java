package bisan.internship.devtrack.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class PasswordResetConfirmRequest {

    // Getters and Setters
    @NotBlank(message = "Token is required")
    private String resetToken;

    @NotBlank(message = "Password is required")
    private String newPassword;

//    // Constructors
//    public PasswordResetConfirmRequest() {}
//
//    public PasswordResetConfirmRequest(String resetToken, String newPassword) {
//        this.resetToken = resetToken;
//        this.newPassword = newPassword;
//    }

}