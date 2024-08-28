package bisan.internship.devtrack.model.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * SecurityUser represents a user in the security context of the application.
 * It implements the UserDetails interface from Spring Security, providing necessary user details
 * for authentication and authorization.
 */
public class SecurityUser implements UserDetails {

    private static final long serialVersionUID = -4363004109103089561L;

    private Long id;

//    private String username;
    private String password;    // User's hashed password
    private String email;       // Principal identifier (used as username)

    // Date when the password was last reset
    private Date lastPasswordReset;

    // Authorities (roles/permissions) granted to the user
    private Collection<? extends GrantedAuthority> authorities;

    // The following flags are part of the UserDetails interface
    // and determine whether the user’s account is active and valid.
    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean credentialsNonExpired = true;
    private Boolean enabled = true;

    public SecurityUser() {
        super();
    }

    public SecurityUser(Long id, String email, String password, Date lastPasswordReset, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;  // Use email here, treated as the username
        this.password = password;
        this.lastPasswordReset = lastPasswordReset;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return email; // Email as username
    }

    public void setUsername(String username) {
        this.email = username; // Username is treated as email
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public Date getLastPasswordReset() {
        return lastPasswordReset;
    }

    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    //accountNonExpired true if the account is not expired
    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @JsonIgnore
    @Override//true if the credentials are not expired
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
