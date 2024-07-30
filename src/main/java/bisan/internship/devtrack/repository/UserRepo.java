package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

}
