package edu.poniperro.nowait.apps.core.api.controller.profile;

import edu.poniperro.nowait.core.profile.user.application.UserBasicDataResponse;
import edu.poniperro.nowait.core.profile.user.application.find.FindUserBasicDataByEmailQuery;
import edu.poniperro.nowait.shared.domain.DomainError;
import edu.poniperro.nowait.shared.domain.bus.command.CommandBus;
import edu.poniperro.nowait.shared.domain.bus.query.QueryBus;
import edu.poniperro.nowait.shared.infraestructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;

@RestController
public class UserBasicDataFindPostController extends ApiController {

    public UserBasicDataFindPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping(path = "/user/find")
    public HashMap<String, Serializable> index(@RequestBody RequestUser request) {

        UserBasicDataResponse response = ask(new FindUserBasicDataByEmailQuery(
                request.getEmail()
        ));
        return response.toPrimitives();
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
