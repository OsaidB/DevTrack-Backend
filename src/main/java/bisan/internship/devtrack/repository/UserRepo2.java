package bisan.internship.devtrack.repository;


//import com.javatab.domain.entity.User;
import bisan.internship.devtrack.model.entity.entity.User_2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo2 extends JpaRepository<User_2, Long> {

    Optional<User_2> findByUsername(String username);

}