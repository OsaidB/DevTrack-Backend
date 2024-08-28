package bisan.internship.devtrack.service.auth;

import bisan.internship.devtrack.dto.auth.AuthRequest;
import bisan.internship.devtrack.dto.auth.AuthResponse;
//import bisan.internship.devtrack.model.entity.entity.User_2;
import bisan.internship.devtrack.dto.auth.RegisterRequest;
import bisan.internship.devtrack.model.entity.FunctionalRole;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.model.security.SecurityUser;
//import bisan.internship.devtrack.repository.UserRepo2;
import bisan.internship.devtrack.repository.FuncRoleRepo;
import bisan.internship.devtrack.repository.UserRepo;
import bisan.internship.devtrack.security.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private FuncRoleRepo funcRoleRepo;
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
        SecurityUser user = (SecurityUser) this.userDetailsService.loadUserByUsername(email);// ensure this method works with email
        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
            return new AuthResponse(this.tokenUtils.refreshToken(token));
        }
        return new AuthResponse();
    }

    @Override
    public User registerUser(RegisterRequest registerRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());
        // Since we're no longer using the username, it is removed from the builder
        FunctionalRole functionalRole = funcRoleRepo.findById(registerRequest.getFunctionalRoleId())
                .orElseThrow(() -> new RuntimeException("FunctionalRole not found"));

        String authorities;
        if (registerRequest.getIsAdmin()) {
            authorities = "ADMIN";
        } else if (registerRequest.getIsTeamLeader()) {
            authorities = "TEAM_LEADER";
        } else {
            authorities = "DEVELOPER";
        }

        // Build a new User instance with the provided details
        User newUser = User.builder()
                .email(registerRequest.getEmail()) // Use email as the identifier
                .password(hashedPassword)
                .firstName(registerRequest.getFirstName()) // Ensure firstName is set
                .lastName(registerRequest.getLastName()) // Ensure lastName is set

                .funcRole(functionalRole)

                .isTeamLeader(registerRequest.getIsTeamLeader()) // Set team leader status
                .lastPasswordReset(new Date()) // Set the last password reset date
                .authorities(authorities) // Set authorities or roles as needed
                .build();
        return userRepo.save(newUser);
    }
}
