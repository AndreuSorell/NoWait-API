package edu.poniperro.nowait.apps.core.api.controller.profile;

//import edu.poniperro.nowait.core.profile.user.application.login.LoginUserQuery;
import edu.poniperro.nowait.core.profile.user.application.login.UserResponse;
import edu.poniperro.nowait.core.shared.infrastructure.security.CustomAuthenticationManager;
import edu.poniperro.nowait.core.shared.infrastructure.security.JwtTokenProvider;
import edu.poniperro.nowait.shared.domain.DomainError;
import edu.poniperro.nowait.shared.domain.bus.command.CommandBus;
import edu.poniperro.nowait.shared.domain.bus.query.QueryBus;
import edu.poniperro.nowait.shared.domain.bus.query.QueryNotRegisteredError;
import edu.poniperro.nowait.shared.infraestructure.spring.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.servlet.http.Cookie;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@PermitAll
@RestController
public final class UserLoginController extends ApiController {

    @Autowired
    private final CustomAuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public UserLoginController(QueryBus queryBus, CommandBus commandBus, CustomAuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        super(queryBus, commandBus);
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(path = "/profile/login")
    public ResponseEntity<String> index(@RequestBody LoginRequest request) throws QueryNotRegisteredError {
        // Autenticación y generación de token JWT
        try {
            // Autenticar al usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            // Generar token JWT
            String token = jwtTokenProvider.generateToken(authentication);

            /*String cookieToken = "token=" + token+"; Max-Age=86400; ";
            MultiValueMap<String, String> headers = new org.springframework.http.HttpHeaders();
            headers.add("Set-Cookie", cookieToken);*/

            return new ResponseEntity<String>(token, HttpStatus.OK);
        } catch (AuthenticationException ex) {
            // Manejo de error de autenticación
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}


final class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
