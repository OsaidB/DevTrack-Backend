package bisan.internship.devtrack.security;

import bisan.internship.devtrack.model.security.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {
//a utility class
// designed to handle JSON Web Tokens (JWTs)
// for authentication and authorization purposes.

    //  private final String AUDIENCE_UNKNOWN   = "unknown";
    private final String AUDIENCE_WEB = "web";

//  private final String AUDIENCE_MOBILE    = "mobile";
//  private final String AUDIENCE_TABLET    = "tablet";

    @Value("${javatab.token.secret}")
    private String secret;

    @Value("${javatab.token.expiration}")
    private Long expiration;

    public String getUsernameFromToken(String token) {//Extracts the username from the JWT token.
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        //Validates the token against the user details,
        // checking username, token expiration, and password reset dates.
        SecurityUser user = (SecurityUser) userDetails;
        final String username = this.getUsernameFromToken(token);
        final Date created = this.getCreatedDateFromToken(token);
        final Date expiration = this.getExpirationDateFromToken(token);
        return (username.equals(user.getUsername()) && !(this.isTokenExpired(token)) && !(this.isCreatedBeforeLastPasswordReset(created, user.getLastPasswordReset())));
        /*
        SecurityUser Casting:
        ensure that the userDetails object is properly cast to SecurityUser.
        If there’s a chance it might not be, add checks to handle such cases.
        */
    }


////////////////////////////////////////////////////////////////////////////    //helper methods for current class


    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }

    /*
    Methods like getClaimsFromToken and generateToken
    handle exceptions and return null.
    Consider logging these exceptions for better debugging.
    */

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
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {//Retrieves the expiration date of the token.
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
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


    public String getAudienceFromToken(String token) {//Retrieves the audience from the token.
        String audience;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            audience = (String) claims.get("audience");
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

////////////////////////////////////////////////////////////////////////////  //used in AuthenticationServiceImpl class:

    //used in AuthenticationServiceImpl class:
    public String generateToken(UserDetails userDetails) {//Generates a new JWT token with the provided user details and device information.
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", userDetails.getUsername());
        claims.put("audience", AUDIENCE_WEB);
        claims.put("created", this.generateCurrentDate());
        return this.generateToken(claims);
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
            refreshedToken = null;
        }
        return refreshedToken;
    }
}
