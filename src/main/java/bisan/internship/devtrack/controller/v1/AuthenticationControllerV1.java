package bisan.internship.devtrack.controller.v1;

//import com.javatab.controller.BaseController;
//import com.javatab.controller.IAuthenticationController;
//import com.javatab.domain.entity.User;
//import com.javatab.dto.request.AuthenticationRequest;
//import com.javatab.dto.response.AuthenticationResponse;
//import com.javatab.service.AuthenticationService;
import bisan.internship.devtrack.controller.BaseController;
import bisan.internship.devtrack.controller.IAuthenticationController;
import bisan.internship.devtrack.dto.auth.AuthRequest;
import bisan.internship.devtrack.dto.auth.AuthResponse;
import bisan.internship.devtrack.model.entity.entity.User_2;
import bisan.internship.devtrack.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class AuthenticationControllerV1 extends BaseController implements IAuthenticationController {


  @Value("${javatab.token.header}")
  private String tokenHeader;

  private final AuthService authenticationService;

  public ResponseEntity<AuthResponse> authenticationRequest(AuthRequest authRequest) {
    return ResponseEntity.ok(this.authenticationService.authenticate(authRequest));
  }

  public ResponseEntity<AuthResponse> authenticationRequest(HttpServletRequest request) {
    return ResponseEntity.ok(this.authenticationService.refreshToken(request.getHeader(tokenHeader)));
  }

  public ResponseEntity<User_2> registerUser(AuthRequest authRequest) {
    return new ResponseEntity<>(this.authenticationService.registerUser(authRequest), HttpStatus.CREATED);
  }

}
