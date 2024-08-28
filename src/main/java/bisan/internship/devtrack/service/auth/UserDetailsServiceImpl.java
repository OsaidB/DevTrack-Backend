package bisan.internship.devtrack.service.auth;

import bisan.internship.devtrack.exception.NoUserFoundException;
//import bisan.internship.devtrack.model.entity.entity.User_2;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.model.factory.UserFactory;
import bisan.internship.devtrack.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User aUser2 = this.userRepo.findByEmail(email)
                .orElseThrow(() -> new NoUserFoundException(String.format("No user found with email '%s'.", email)));
        return UserFactory.create(aUser2);
    }

}
