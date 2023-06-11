package edu.poniperro.nowait.apps.core.api.controller.profile;

import edu.poniperro.nowait.core.profile.user.application.create.CreateUserCommand;
import edu.poniperro.nowait.core.profile.user.application.search.SearchUserEmailQuery;
import edu.poniperro.nowait.core.profile.user.application.search.SearchUserEmailResponse;
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

import javax.annotation.security.PermitAll;
import java.time.LocalDateTime;
import java.util.HashMap;

@PermitAll
@RestController
public final class UserCreatePostController extends ApiController {

    public UserCreatePostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PermitAll
    @PostMapping(path = "/profile/create")
    public ResponseEntity<String> index(@RequestBody RequestUser request) throws CommandNotRegisteredError {
        SearchUserEmailResponse response = ask(new SearchUserEmailQuery(request.getEmail()));
        if (!response.getEmail().equals("")) {
            return new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT); // 409
        }
        else {
            dispatch(new CreateUserCommand(
                    request.getName(),
                    request.getEmail(),
                    request.getPassword(),
                    request.getAnonymous(),
                    "standard",
                    LocalDateTime.now().toString()));

            return new ResponseEntity<String>("User created", HttpStatus.CREATED); // 201
        }
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}