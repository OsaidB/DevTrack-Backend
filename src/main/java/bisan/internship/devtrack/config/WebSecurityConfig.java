package bisan.internship.devtrack.config;

import bisan.internship.devtrack.security.AuthenticationTokenFilter;
import bisan.internship.devtrack.security.EntryPointUnauthorizedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final EntryPointUnauthorizedHandler unauthorizedHandler;
    private final AuthenticationConfiguration authConfig;
    private final AuthenticationTokenFilter authenticationTokenFilter;

    //This bean provides password encoding using BCrypt, which is a secure way to store passwords.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //The AuthenticationManager is responsible for processing authentication requests.
    // It's configured through the AuthenticationConfiguration.
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfig.getAuthenticationManager();
    }

    //The SecurityFilterChain method configures how HTTP requests are secured.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                //CSRF protection is disabled,
                // which is typical for APIs where tokens (like JWT) are used for security.
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //The application is stateless,
                // meaning no session is created or used.
                // This is common in token-based authentication systems like JWT.
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                //.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                                .requestMatchers(//This part configures which endpoints are publicly accessible(without authentication):
                                        antMatcher("/**/api-docs/**"),
                                        antMatcher("/swagger-ui.html"),
                                        antMatcher("/swagger-ui/**"),
                                        antMatcher("/auth/**")).permitAll() //https://marco.dev/spring-boot-h2-error
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/leader/**").hasRole("TEAM_LEADER")
                                .requestMatchers("/developer/**").hasRole("DEVELOPER")
                                .anyRequest().authenticated())//All other endpoints require the user to be authenticated.
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(this.unauthorizedHandler));
        //This configures a custom EntryPointUnauthorizedHandler to handle unauthorized access attempts,
        //any request that fails authentication
        // will result in this handler being invoked,
        // and the client will receive a 401 Unauthorized response.

        // Custom JWT based authentication:
        //This line adds a custom filter (AuthenticationTokenFilter) before the UsernamePasswordAuthenticationFilter.
        // This filter will intercept requests and check for a valid JWT token in the header,
        // authenticating the user if the token is valid.
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //Given the functionality of the AuthenticationTokenFilter class,
        // you've already integrated it correctly in the WebSecurityConfiguration by adding it before the UsernamePasswordAuthenticationFilter.
        // This setup ensures that every request passes through this filter, enabling token-based authentication.
        return http.build();

    }
}
/*
3. What's Missing for Role-Based Access Control?
To enforce role-based access control, you would typically define specific access rules for different roles. For example:


http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
    authorizationManagerRequestMatcherRegistry
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .requestMatchers("/leader/**").hasRole("TEAM_LEADER")
        .requestMatchers("/developer/**").hasRole("DEVELOPER")
        .anyRequest().authenticated());


4. Next Steps
Ensure that your User entity and UserDetailsServiceImpl are correctly setting up roles.
Consider adjusting the authorizeHttpRequests configuration to include role-based restrictions as needed.
Verify that the AuthenticationTokenFilter is correctly extracting and validating roles from the JWT.
*/