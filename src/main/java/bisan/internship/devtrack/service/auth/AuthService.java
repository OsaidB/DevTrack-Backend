package bisan.internship.devtrack.service.auth;


import bisan.internship.devtrack.dto.auth.AuthRequest;
import bisan.internship.devtrack.dto.auth.AuthResponse;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.dto.auth.RegisterRequest;
import bisan.internship.devtrack.dto.auth.PasswordResetRequest;
import bisan.internship.devtrack.dto.auth.PasswordResetConfirmRequest;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);

    AuthResponse refreshToken(String token);

    User registerUser(RegisterRequest registerRequest);

    // New methods for password reset
    void requestPasswordReset(PasswordResetRequest passwordResetRequest);
//    void requestPasswordReset(String email);

    void confirmPasswordReset(PasswordResetConfirmRequest passwordResetConfirmRequest);
}
