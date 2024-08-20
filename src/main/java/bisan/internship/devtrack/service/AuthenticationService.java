package bisan.internship.devtrack.service;

//import com.javatab.domain.entity.User;
//import com.javatab.dto.request.AuthenticationRequest;
//import com.javatab.dto.response.AuthenticationResponse;

import bisan.internship.devtrack.dto.request.AuthenticationRequest;
import bisan.internship.devtrack.dto.response.AuthenticationResponse;
import bisan.internship.devtrack.model.entity.entity.User;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    AuthenticationResponse refreshToken(String token);
    User registerUser(AuthenticationRequest authenticationRequest);
}
