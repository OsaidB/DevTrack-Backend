package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.exception.NoUserFoundException;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.model.factory.UserFactory;
import bisan.internship.devtrack.repository.UserRepo;
//import com.javatab.domain.entity.User;
//import com.javatab.exception.NoUserFoundException;
//import com.javatab.model.factory.UserFactory;
//import com.javatab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepo userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User aUser = this.userRepository
            .findByUsername(username)
            .orElseThrow(
                    () -> new NoUserFoundException(String.format("No user found with username '%s'.", username)));
    return UserFactory.create(aUser);
  }

}
