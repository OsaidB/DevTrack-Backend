package bisan.internship.devtrack.model.factory;


import bisan.internship.devtrack.model.entity.entity.User_2;
import bisan.internship.devtrack.model.security.SecurityUser;
import org.springframework.security.core.authority.AuthorityUtils;

public class UserFactory {

  public static SecurityUser create(User_2 user2) {
    // Converts a User_2 entity
    // into a SecurityUser object,
    // which is used by Spring Security

    return new SecurityUser(
      user2.getId(),
//      user2.getUsername(),
      user2.getPassword(),
      user2.getEmail(),//(used as the identifier)
      user2.getLastPasswordReset(),
      AuthorityUtils.commaSeparatedStringToAuthorityList(user2.getAuthorities())
            //// Converts comma-separated authorities into a list of GrantedAuthority objects
    );
  }

}
