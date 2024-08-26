package bisan.internship.devtrack.service.impl;

//import com.javatab.domain.entity.User;
//import com.javatab.dto.request.AuthenticationRequest;
//import com.javatab.dto.response.AuthenticationResponse;
//import com.javatab.model.security.SecurityUser;
//import com.javatab.repository.UserRepository;
//import com.javatab.security.TokenUtils;
//import com.javatab.service.AuthenticationService;
import bisan.internship.devtrack.dto.request.AuthenticationRequest;
import bisan.internship.devtrack.dto.response.AuthenticationResponse;
import bisan.internship.devtrack.model.entity.entity.User_2;
import bisan.internship.devtrack.model.security.SecurityUser;
import bisan.internship.devtrack.repository.UserRepo2;
import bisan.internship.devtrack.security.TokenUtils;
import bisan.internship.devtrack.service.AuthenticationService;
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
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;
    private final UserDetailsService userDetailsService;
    private final UserRepo2 userRepo2;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        // Perform the authentication
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-authentication so we can generate token
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//        return new AuthenticationResponse(Objects.requireNonNull(this.tokenUtils.generateToken(userDetails, authenticationRequest.getDevice())));
        return new AuthenticationResponse(Objects.requireNonNull(this.tokenUtils.generateToken(userDetails)));
    }

    @Override
    public AuthenticationResponse refreshToken(String token) {
        String username = this.tokenUtils.getUsernameFromToken(token);
        SecurityUser user = (SecurityUser) this.userDetailsService.loadUserByUsername(username);
        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
           return new AuthenticationResponse(this.tokenUtils.refreshToken(token));
        }
        return new AuthenticationResponse();
    }

    @Override
    public User_2 registerUser(AuthenticationRequest authenticationRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(authenticationRequest.getPassword());
        User_2 newUser2 = User_2.builder()
                .username(authenticationRequest.getUsername())
                .password(hashedPassword)
                .email(authenticationRequest.getEmail())
                .lastPasswordReset(new Date())
                .authorities("ADMIN")
                .build();
        return userRepo2.save(newUser2);
    }
}
