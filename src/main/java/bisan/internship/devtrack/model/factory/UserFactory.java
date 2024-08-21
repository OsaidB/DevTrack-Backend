package bisan.internship.devtrack.model.factory;

//import com.javatab.domain.entity.User;
//import com.javatab.model.security.SecurityUser;
import bisan.internship.devtrack.model.entity.entity.User_2;
import bisan.internship.devtrack.model.security.SecurityUser;
import org.springframework.security.core.authority.AuthorityUtils;

public class UserFactory {

  public static SecurityUser create(User_2 user2) {
    return new SecurityUser(
      user2.getId(),
      user2.getUsername(),
      user2.getPassword(),
      user2.getEmail(),
      user2.getLastPasswordReset(),
      AuthorityUtils.commaSeparatedStringToAuthorityList(user2.getAuthorities())
    );
  }

}
