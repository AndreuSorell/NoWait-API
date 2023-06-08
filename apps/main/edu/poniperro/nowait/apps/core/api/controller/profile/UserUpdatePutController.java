package edu.poniperro.nowait.apps.core.api.controller.profile;

import edu.poniperro.nowait.core.profile.user.application.UserResponse;
import edu.poniperro.nowait.core.profile.user.application.update.UpdateUserCommand;
import edu.poniperro.nowait.core.shared.infrastructure.security.JwtTokenProvider;
import edu.poniperro.nowait.shared.domain.DomainError;
import edu.poniperro.nowait.shared.domain.bus.command.CommandBus;
import edu.poniperro.nowait.shared.domain.bus.command.CommandNotRegisteredError;
import edu.poniperro.nowait.shared.domain.bus.query.QueryBus;
import edu.poniperro.nowait.shared.infraestructure.spring.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import java.io.Serializable;
import java.util.HashMap;

@RestController
public final class UserUpdatePutController extends ApiController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public UserUpdatePutController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PutMapping(path = "/profile/update")
    public ResponseEntity index(@RequestBody RequestUser request){
        dispatch(new UpdateUserCommand(
                request.getName(),
                jwtTokenProvider.getEmailFromToken(request.getToken()),
                request.getPassword(),
                request.getAnonymous()
        ));

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
