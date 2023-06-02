package edu.poniperro.nowait.apps.core.api.controller.profile;

import edu.poniperro.nowait.core.profile.user.application.create.CreateUserCommand;
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
public final class UserCreatePostController extends ApiController {

    public UserCreatePostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping(path = "/profile/create")
    public ResponseEntity index(@RequestBody RequestUser request) throws CommandNotRegisteredError {
        dispatch(new CreateUserCommand(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getAnonymous(),
                request.getType(),
                request.getCreationDate()));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}

final class RequestUser {
    private String name;
    private String email;
    private String password;
    private String anonymous;
    private String type;
    private String creationDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}