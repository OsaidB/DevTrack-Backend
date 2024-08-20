//package bisan.internship.devtrack.security;
//
////import bisan.internship.devtrack.model.entity.User;
//import bisan.internship.devtrack.model.entity.User;
//import bisan.internship.devtrack.repository.UserRepo;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.CurrentSecurityContext;
//
//import com.vaadin.flow.spring.security.AuthenticationContext;
//
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Optional;
//
//@Component
//public class AuthenticatedUser {
//
//    private final UserRepo userRepository;
//
//    public AuthenticatedUser(AuthenticationContext authenticationContext, UserRepo userRepository) {
//        this.userRepository = userRepository;
//        this.authenticationContext = authenticationContext;
//    }
//
//    @Transactional
//    public Optional<User> get() {
//        return authenticationContext.getAuthenticatedUser(UserDetails.class)
//                .map(userDetails -> userRepository.findByUsername(userDetails.getUsername()));
//    }
//
//
//    public void logout() {
//        authenticationContext.logout();
//    }
//
//}
