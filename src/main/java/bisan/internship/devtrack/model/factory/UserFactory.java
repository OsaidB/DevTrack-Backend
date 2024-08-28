package bisan.internship.devtrack.model.factory;


//import bisan.internship.devtrack.model.entity.entity.User_2;

import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.model.security.SecurityUser;
import org.springframework.security.core.authority.AuthorityUtils;

public class UserFactory {

    public static SecurityUser create(User user2) {
        // Converts a User entity
        // into a SecurityUser object,
        // which is used by Spring Security

        return new SecurityUser(
                user2.getId(),
//      user2.getUsername(),
                user2.getEmail(),// we use email here, which is treated as the username in SecurityUser
                //we take it from user entity as an email, but then take it from securityUser as username

                user2.getPassword(),
                user2.getLastPasswordReset(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(user2.getAuthorities())
                //// Converts comma-separated authorities into a list of GrantedAuthority objects
        );
    }


}

/*

In this process,

you've already mapped
the email from User
to the username in SecurityUser,

which is necessary since Spring Security will still work with getUsername():

*/
