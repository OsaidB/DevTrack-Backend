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
    private String email;

    // Date when the password was last reset
    private Date lastPasswordReset;

    // Authorities (roles/permissions) granted to the user
    private Collection<? extends GrantedAuthority> authorities;

    //the following flags are part of the UserDetails interface
    // and determine whether the user’s account is active and valid.
    private Boolean accountNonExpired = true;  // Flag indicating if the account is not expired
    private Boolean accountNonLocked = true;  // Flag indicating if the account is not locked
    private Boolean credentialsNonExpired = true;  // Flag indicating if the credentials are not expired
    private Boolean enabled = true;  // Flag indicating if the account is enabled

    public SecurityUser() {
        super();
    }

    public SecurityUser(Long id, String password, String email, Date lastPasswordReset, Collection<? extends GrantedAuthority> authorities) {
        this.setId(id);
//        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setLastPasswordReset(lastPasswordReset);
        this.setAuthorities(authorities);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override//Returns the email as the principal (username equivalent) for authentication.
    public String getUsername() {
        return this.getEmail();
    }

//    public void setUsername(String username) {
//        this.username = username;
//    }

    @JsonIgnore//This method is ignored during serialization.
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore//Gets the date when the password was last reset. This method is ignored during serialization.
    public Date getLastPasswordReset() {
        return this.lastPasswordReset;
    }

    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    @Override//Gets the authorities (roles/permissions) granted to the user.
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @JsonIgnore//Gets the flag indicating if the account is not expired.
    // This method is ignored during serialization.
    public Boolean getAccountNonExpired() {
        return this.accountNonExpired;
    }

    //accountNonExpired true if the account is not expired
    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.getAccountNonExpired();
    }

    @JsonIgnore
    public Boolean getAccountNonLocked() {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override//true if the account is not locked
    public boolean isAccountNonLocked() {
        return this.getAccountNonLocked();
    }

    @JsonIgnore//true if the credentials are not expired
    public Boolean getCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override//true if the credentials are not expired
    public boolean isCredentialsNonExpired() {
        return this.getCredentialsNonExpired();
    }

    @JsonIgnore
    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.getEnabled();
    }

}
