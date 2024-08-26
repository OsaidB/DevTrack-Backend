package bisan.internship.devtrack.service.auth;

import bisan.internship.devtrack.dto.auth.AuthRequest;
import bisan.internship.devtrack.dto.auth.AuthResponse;
import bisan.internship.devtrack.model.entity.entity.User_2;
import bisan.internship.devtrack.model.security.SecurityUser;
import bisan.internship.devtrack.repository.UserRepo2;
import bisan.internship.devtrack.security.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService/*custom class*/ {

    private final AuthenticationManager authManager;//prebuilt from spring security

    private final TokenUtils tokenUtils;
    private final UserDetailsService userDetailsService;    //prebuilt from spring security

    private final UserRepo2 userRepo2;

    /**
     * Authenticates the user based on provided credentials and generates a JWT token.
     *
     * @param authRequest the authentication request containing username and password
     * @return an AuthenticationResponse containing the JWT token
     */
    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        // Perform the authentication using email instead of username
        Authentication authentication = this.authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-authentication so we can generate token
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authRequest.getEmail());

        //return new AuthenticationResponse(Objects.requireNonNull(this.tokenUtils.generateToken(userDetails, authenticationRequest.getDevice())));
        return new AuthResponse(Objects.requireNonNull(this.tokenUtils.generateToken(userDetails)));
    }

    @Override
    public AuthResponse refreshToken(String token) {
        String email = this.tokenUtils.getUsernameFromToken(token);//username is actually the email
        SecurityUser user = (SecurityUser) this.userDetailsService.loadUserByUsername(email);//username is actually the email
        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
            return new AuthResponse(this.tokenUtils.refreshToken(token));
        }
        return new AuthResponse();
    }

    @Override
    public User_2 registerUser(AuthRequest authRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(authRequest.getPassword());
        // Since we're no longer using the username, it is removed from the builder
        User_2 newUser2 = User_2.builder()
                .password(hashedPassword)
                .email(authRequest.getEmail()) // Use email as the identifier
                .lastPasswordReset(new Date())
                .authorities("ADMIN") // Set authorities as needed
                .build();
        return userRepo2.save(newUser2);
    }
}
