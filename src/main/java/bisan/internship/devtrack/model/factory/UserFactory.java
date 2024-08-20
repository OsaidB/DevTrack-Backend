package bisan.internship.devtrack.model.factory;

//import com.javatab.domain.entity.User;
//import com.javatab.model.security.SecurityUser;
import bisan.internship.devtrack.model.entity.entity.User;
import bisan.internship.devtrack.model.security.SecurityUser;
import org.springframework.security.core.authority.AuthorityUtils;

public class UserFactory {

  public static SecurityUser create(User user) {
    return new SecurityUser(
      user.getId(),
      user.getUsername(),
      user.getPassword(),
      user.getEmail(),
      user.getLastPasswordReset(),
      AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
    );
  }

}
