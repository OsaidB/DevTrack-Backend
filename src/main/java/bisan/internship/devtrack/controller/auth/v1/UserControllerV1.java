package bisan.internship.devtrack.controller.auth.v1;

import bisan.internship.devtrack.dto.UserDTO;
import bisan.internship.devtrack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users") // Add this annotation to handle routing
public class UserControllerV1 extends BaseController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable("username") String username) {
        UserDTO aUser = userService.getUserByUsername(username);
        return ResponseEntity.ok(aUser);
    }
}
