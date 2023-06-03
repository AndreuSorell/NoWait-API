package edu.poniperro.nowait.apps.core.api.controller.profile;

import edu.poniperro.nowait.shared.domain.DomainError;
import edu.poniperro.nowait.shared.domain.bus.command.CommandBus;
import edu.poniperro.nowait.shared.domain.bus.command.CommandNotRegisteredError;
import edu.poniperro.nowait.shared.domain.bus.query.QueryBus;
import edu.poniperro.nowait.shared.infraestructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public final class UserLoginController extends ApiController {

    public UserLoginController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping(path = "/profile/login")
    public ResponseEntity login(@RequestBody LoginRequest request) throws CommandNotRegisteredError {
      //  dispatch(new UserLoginQuery(request.email(), request.password()));

        // Lógica adicional de autenticación y generación de token JWT

        return new ResponseEntity<>(HttpStatus.OK);
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
