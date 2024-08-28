package bisan.internship.devtrack.security;

import bisan.internship.devtrack.model.security.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TokenUtils {
//a utility class
// designed to handle JSON Web Tokens (JWTs)
// for authentication and authorization purposes.

    /*

    1- SecurityUser: (custom class) represents the user details used in authentication.
    2- UserDetails: (part of Spring Security) represents the user’s authentication information.

    3- Claims: (from the io.jsonwebtoken library) represents the claims of the JWT.
    4- Jwts: (from the io.jsonwebtoken library) provides methods for creating and parsing JWTs.
    5- SignatureAlgorithm: (from the io.jsonwebtoken library) defines the signature algorithm used for signing the JWT.

    */
    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);
    private static final String AUDIENCE_WEB = "web";

    @Value("${javatab.token.secret}")
    private String secret;

    @Value("${javatab.token.expiration}")
    private Long expiration;

    public String getUsernameFromToken(String token) {//Extracts the username from the JWT token.
        String username = null;
        try {
            logger.info("Token: {}", token);

            final Claims claims = this.getClaimsFromToken(token);
//            assert claims != null;
            if (claims != null) {
                username = claims.getSubject();
            } else {
                logger.info("Claims are null");
            }
        } catch (Exception e) {
            logger.error("Error getting username from token: {}", e.getMessage());
        }
        return username;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        logger.info("validateToken called: {}", userDetails);


        //Validates the token against the user details,
        // checking username, token expiration, and password reset dates.
        SecurityUser secUser = (SecurityUser) userDetails;
        final String username = this.getUsernameFromToken(token);
        logger.info("        final String username = this.getUsernameFromToken(token): {}", username);

        final Date created = this.getCreatedDateFromToken(token);
        final Date expiration = this.getExpirationDateFromToken(token);
//        return (username.equals(user.getUsername()) && !(this.isTokenExpired(token)) && !(this.isCreatedBeforeLastPasswordReset(created, user.getLastPasswordReset())));
        //     * @return True if the token is valid for the given user details, false otherwise.
        final String role = this.getRolesFromToken(token); // Adjusted to get a single role

        /*
        SecurityUser Casting:
        ensure that the userDetails object is properly cast to SecurityUser.
        If there’s a chance it might not be, add checks to handle such cases.
        */
        // Validate roles and other aspects
        return (username.equals(secUser.getUsername()) && !this.isTokenExpired(token)
                &&
                !this.isCreatedBeforeLastPasswordReset(created, secUser.getLastPasswordReset())
                &&
                role.equals(secUser.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("DEFAULT_ROLE")));
    }


////////////////////////////////////////////////////////////////////////////    //helper methods for current class


    private Claims getClaimsFromToken(String token) {
        logger.info("SecretSecretSecretSecretSecret: {}", secret); // Ensure the secret is as expected

//        Claims claims = null;
        try {
            logger.info("Token received for parsing: {}", token);

//            if (token == null) {
//                logger.error("token == null");
//            }
//            if (!token.contains(".")) {
//                logger.error("!token.contains(\".\")");
//            }
//            if (token.contains("{{")) {
//                logger.error("token.contains(\"{{\")");
//            }
            return Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();

        } catch (Exception e) {
            logger.error("Error getting claims from token: {}", e.getMessage());
            return null;
        }
//        return claims;//@return The claims extracted from the token, or null if an error occurs.
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }

    public Date getCreatedDateFromToken(String token) {//Retrieves the creation date of the token.
        Date created;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            created = new Date((Long) claims.get("created"));
        } catch (Exception e) {
            logger.error("Error getting created date from token: {}", e.getMessage());
            created = null;
        }
        return created;
        //@return The creation date extracted from the token, or null if an error occurs.
    }

    public Date getExpirationDateFromToken(String token) {//Retrieves the expiration date of the token.
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            logger.error("Error getting expiration date from token: {}", e.getMessage());
            expiration = null;
        }
        return expiration;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }


////////////////////////////////////////////////////////////////////////////  //used in AuthenticationServiceImpl class:

    //used in AuthenticationServiceImpl class:
    public String generateToken(UserDetails userDetails) {//Generates a new JWT token with the provided user details and device information.
        Map<String, Object> claims = new HashMap<String, Object>();

        logger.info("sub: {}", userDetails.getUsername());

        claims.put("sub", userDetails.getUsername()); //username is actually the email
        claims.put("audience", AUDIENCE_WEB);
        claims.put("created", this.generateCurrentDate());
//        claims.put("roles", userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList()));

        // Assuming userDetails.getAuthorities() has only one authority
        String authority = userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("DEFAULT_ROLE");
        claims.put("role", authority);

        return this.generateToken(claims);
    }

    public String getRolesFromToken(String token) {
        Claims claims = this.getClaimsFromToken(token);
        return claims.get("role", String.class); // Extract the role claim from the token
    }

    private String generateToken(Map<String, Object> claims) {
        logger.info("chinaaaaaaaaaaaa: {}", claims);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }

    //used in AuthenticationServiceImpl class:
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = this.getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset) && !isTokenExpired(token);
    }

    //used in AuthenticationServiceImpl class:
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            claims.put("created", this.generateCurrentDate());
            refreshedToken = this.generateToken(claims);
        } catch (Exception e) {
            logger.error("Error refreshing token: {}", e.getMessage());
            refreshedToken = null;
        }
        return refreshedToken;
    }
}
