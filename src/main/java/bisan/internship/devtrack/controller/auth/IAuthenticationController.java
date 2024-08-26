package bisan.internship.devtrack.controller.auth;

//import com.javatab.dto.request.AuthenticationRequest;
import bisan.internship.devtrack.dto.auth.AuthRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/auth")
public interface IAuthenticationController {

    @SecurityRequirements
    @PostMapping()
    ResponseEntity<?> authenticationRequest(@RequestBody @Valid AuthRequest authRequest);
    @GetMapping("/refresh")
    ResponseEntity<?> authenticationRequest(HttpServletRequest request);
    @SecurityRequirements
    @PostMapping("/register")
    ResponseEntity<?> registerUser(@RequestBody @Valid AuthRequest authRequest);
}
