package bisan.internship.devtrack.controller;

//import com.javatab.domain.entity.User;
//import com.javatab.exception.NoUserFoundException;
//import bisan.internship.devtrack.model.entity.entity.User;
import bisan.internship.devtrack.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/users")
public interface IUserController {

    @GetMapping()
    ResponseEntity<List<UserDTO>> getAllUsers();
    @GetMapping("/{username}")
    ResponseEntity<UserDTO> getUserByName(@PathVariable("username") String username);
}
