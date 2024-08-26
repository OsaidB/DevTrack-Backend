package bisan.internship.devtrack.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


/*

The EntryPointUnauthorizedHandler
is triggered whenever an unauthenticated user tries to access a protected resource
(an endpoint that requires authentication).


*/
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
  }
/*

The commence method is overridden,
which is called by Spring Security when an AuthenticationException is thrown.

In this method, the response is set to return an HTTP 401 Unauthorized status with a message "Access Denied".
*/
}
