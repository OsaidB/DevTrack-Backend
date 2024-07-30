package bisan.internship.devtrack.controller;


import bisan.internship.devtrack.dto.UserDTO;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Build Add User REST API
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto){
        UserDTO createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

//    @GetMapping("/get-user")
//    public User getUser(@RequestParam Long id){
//    return  userService.getUser(id);
//    }

}
