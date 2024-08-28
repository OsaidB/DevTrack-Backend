package bisan.internship.devtrack.service.auth;


import bisan.internship.devtrack.dto.auth.AuthRequest;
import bisan.internship.devtrack.dto.auth.AuthResponse;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.dto.auth.RegisterRequest;
//import bisan.internship.devtrack.model.entity.entity.User_2;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);

    AuthResponse refreshToken(String token);

    User registerUser(RegisterRequest registerRequest);
}
