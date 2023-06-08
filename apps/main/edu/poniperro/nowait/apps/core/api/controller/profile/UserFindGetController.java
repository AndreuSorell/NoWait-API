package edu.poniperro.nowait.apps.core.api.controller.profile;

import edu.poniperro.nowait.core.profile.user.application.find.FindUserByEmailQuery;
import edu.poniperro.nowait.core.profile.user.application.UserResponse;
import edu.poniperro.nowait.core.shared.infrastructure.security.JwtTokenProvider;
import edu.poniperro.nowait.shared.domain.DomainError;
import edu.poniperro.nowait.shared.domain.bus.command.CommandBus;
import edu.poniperro.nowait.shared.domain.bus.command.CommandNotRegisteredError;
import edu.poniperro.nowait.shared.domain.bus.query.QueryBus;
import edu.poniperro.nowait.shared.infraestructure.spring.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;

@RestController
public class UserFindGetController extends ApiController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public UserFindGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping(path = "/profile/find")
    public HashMap<String, Serializable> index(@RequestBody RequestUserEmail request) throws CommandNotRegisteredError {

        UserResponse response = ask(new FindUserByEmailQuery(
                jwtTokenProvider.getEmailFromToken(request.getToken())
        ));
        return response.toPrimitives();
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}

final class RequestUserEmail {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
