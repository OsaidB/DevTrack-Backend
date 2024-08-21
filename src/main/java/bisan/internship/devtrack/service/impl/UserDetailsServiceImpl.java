package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.exception.NoUserFoundException;
import bisan.internship.devtrack.model.entity.entity.User_2;
import bisan.internship.devtrack.model.factory.UserFactory;
//import com.javatab.domain.entity.User;
//import com.javatab.exception.NoUserFoundException;
//import com.javatab.model.factory.UserFactory;
//import com.javatab.repository.UserRepository;
import bisan.internship.devtrack.repository.UserRepo2;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo2 userRepo2;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User_2 aUser2 = this.userRepo2.findByUsername(username).orElseThrow( () -> new NoUserFoundException(String.format("No user found with username '%s'.", username)));
        return UserFactory.create(aUser2);
    }

}
