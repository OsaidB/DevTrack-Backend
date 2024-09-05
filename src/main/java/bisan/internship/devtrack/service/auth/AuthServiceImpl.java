package bisan.internship.devtrack.service.auth;

import bisan.internship.devtrack.dto.auth.AuthRequest;
import bisan.internship.devtrack.dto.auth.AuthResponse;
import bisan.internship.devtrack.dto.auth.PasswordResetRequest;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

import bisan.internship.devtrack.dto.auth.PasswordResetConfirmRequest;
import bisan.internship.devtrack.dto.auth.RegisterRequest;
import bisan.internship.devtrack.model.entity.FunctionalRole;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.model.security.SecurityUser;
import bisan.internship.devtrack.repository.UserRepo;
import bisan.internship.devtrack.repository.FuncRoleRepo;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService/*custom class*/ {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);


    private final AuthenticationManager authManager;//prebuilt from spring security

    private final TokenUtils tokenUtils;
    private final UserDetailsService userDetailsService;    //prebuilt from spring security

    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final FuncRoleRepo funcRoleRepo;
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

    // New method to initiate password reset

    @Override
    public void requestPasswordReset(PasswordResetRequest passwordResetRequest) {
        // Extract email from the DTO
        String email = passwordResetRequest.getEmail();

        // Fetch the user from the repository
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a SecurityUser instance
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUsername(user.getEmail()); // Use email as username
        securityUser.setLastPasswordReset(user.getLastPasswordReset());
        securityUser.setPassword(user.getPassword()); // Ensure to use hashed password
        securityUser.setAuthorities(Collections.emptyList()); // Set as needed

        // Generate a password reset token
        String resetToken = tokenUtils.generatePasswordResetToken(securityUser);

        // Send the reset token to the user
        sendPasswordResetEmail(user.getEmail(), resetToken);

        // Optionally update the user entity to record the token and expiration
        // user.setResetToken(resetToken); // Ensure this field exists in your User entity
        // user.setTokenExpiration(calculateTokenExpiration()); // Implement this method to set expiration time
        userRepo.save(user);
    }

    // Helper method to calculate token expiration
    private Date calculateTokenExpiration() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1); // Set token expiration to 1 hour from now
        return calendar.getTime();
    }

    // Helper method to send the password reset email
    private void sendPasswordResetEmail(String email, String resetToken) {
        // Define email properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP server address for Gmail
        props.put("mail.smtp.port", "587"); // Port for TLS
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Use TLS

        // Create a session with an Authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // Consider using environment variables or secure storage for credentials
//                String username = "o.osaidb2015@gmail.com";
//                String password = "O.osaidb2022@gmail.co0599783048*osa-_-aso";
//                return new PasswordAuthentication(username, password);
                return new PasswordAuthentication("o.osaidb2015@gmail.com", "tsdk rioy itfd mmvu");

            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("o.osaidb2015@gmail.com")); // Replace with your email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Password Reset Request");

            // Create the email content
//            String emailContent = String.format(
//                    "Dear User,<br><br>" +
//                            "We received a request to reset your password. Please use the following link to reset it:<br>" +
//                            "<a href='http://localhost:5173/reset-password-confirmation?token=%s'>Reset Password</a><br><br>" +
//                            "If you did not request this, please ignore this email.<br><br>" +
//                            "Best regards,<br>" +
//                            "DevTrack Team",
//                    resetToken
//            );

            String emailContent = String.format(
                    "Dear User,<br><br>" +
                            "We received a request to reset your password. Please use the following link to reset it:<br>" +
                            "<a href='http://localhost:5173/reset-password-confirmation?token=%s'>token=%s Reset Password</a><br><br>" +
                            "If you did not request this, please ignore this email.<br><br>" +
                            "Best regards,<br>" +
                            "DevTrack Team",
                    resetToken,resetToken
            );

            message.setContent(emailContent, "text/html; charset=utf-8");

            // Send the message
            Transport.send(message);

            System.out.println("Password reset email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Failed to send password reset email.");
        }
    }



    @Override
    public void confirmPasswordReset(PasswordResetConfirmRequest passwordResetConfirmRequest) {
        String resetToken = passwordResetConfirmRequest.getResetToken();
        String newPassword = passwordResetConfirmRequest.getNewPassword();

        // Extract email from the token
        String email = tokenUtils.getUsernameFromToken(resetToken);

        // Load the user from the repository
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a SecurityUser instance for verification
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUsername(email);
        securityUser.setLastPasswordReset(user.getLastPasswordReset());
        securityUser.setPassword(user.getPassword());
        securityUser.setAuthorities(Collections.emptyList()); // Set as needed

        // Verify the reset token
        if (!tokenUtils.verifyPasswordResetToken(resetToken, securityUser)) {
            throw new RuntimeException("Invalid or expired token");
        }

        // Encrypt the new password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(hashedPassword);
        user.setLastPasswordReset(new Date());

        // Clear reset token and expiration
//        user.setResetToken(null); // Ensure these fields exist in the User entity
//        user.setTokenExpiration(null);

        // Save the updated user
        userRepo.save(user);
    }


}
