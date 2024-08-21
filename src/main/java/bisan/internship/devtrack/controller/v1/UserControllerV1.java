package bisan.internship.devtrack.controller.v1;

//import com.javatab.controller.BaseController;
//import com.javatab.controller.IUserController;
//import com.javatab.domain.entity.User;
//import com.javatab.exception.NoUserFoundException;
//import com.javatab.service.UserService;
import bisan.internship.devtrack.controller.BaseController;
import bisan.internship.devtrack.controller.IUserController;
import bisan.internship.devtrack.dto.UserDTO;
import bisan.internship.devtrack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserControllerV1 extends BaseController implements IUserController {

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
