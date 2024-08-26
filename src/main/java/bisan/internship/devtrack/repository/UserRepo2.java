package bisan.internship.devtrack.repository;


import bisan.internship.devtrack.model.entity.entity.User_2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo2 extends JpaRepository<User_2, Long> {

//    Optional<User_2> findByUsername(String username);

    // New method to find a user by their email
    Optional<User_2> findByEmail(String email);
}