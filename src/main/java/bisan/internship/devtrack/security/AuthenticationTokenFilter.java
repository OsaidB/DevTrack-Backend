package bisan.internship.devtrack.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Configuration
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    /*
    this class is responsible for processing JWT tokens in incoming HTTP requests,
    extracting the username,
    validating the token,
    and setting up the Spring Security context
    (with the authenticated user's details.)

    */
    @Value("${javatab.token.header}")
    private String tokenHeader;

    private final TokenUtils tokenUtils; //a custom class that is designed to handle JSON Web Tokens (JWTs)
    // for authentication and authorization purposes.

    private final UserDetailsService userDetailsService;//is used to load user-specific data during authentication

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //The doFilterInternal method extracts the token
        // from the HTTP request header
        // using the tokenHeader property.

//      HttpServletRequest httpRequest = request;
        String authToken = request.getHeader(tokenHeader);

        if (authToken == null || authToken.isEmpty()) {
            logger.warn("Authorization header is missing or empty.");
            filterChain.doFilter(request, response);
            return;
        }


        String email  = this.tokenUtils.getUsernameFromToken(authToken);//username is actually the email
        //The tokenUtils.getUsernameFromToken(authToken) method
        // retrieves the username embedded within the JWT token.

        if (email  != null && SecurityContextHolder.getContext().getAuthentication() == null) {//If the username is valid and there's no existing authentication in the SecurityContext

            try {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(email );//the UserDetailsService loads the user details.

                if (this.tokenUtils.validateToken(authToken, userDetails)) {//The token is then validated using tokenUtils.validateToken(authToken, userDetails).
                    //Security Context Setup:
                    //If the token is valid (the if condition),
                    // -> a UsernamePasswordAuthenticationToken is created
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);// and set into the SecurityContextHolder (marking the user as authenticated for the request.)
                }
            } catch (Exception e) {
                logger.error("Authentication failed for email: " + email , e);
            }
        }

        filterChain.doFilter(request, response);//ensures that the request continues to the next filter in the chain.
    }

}
