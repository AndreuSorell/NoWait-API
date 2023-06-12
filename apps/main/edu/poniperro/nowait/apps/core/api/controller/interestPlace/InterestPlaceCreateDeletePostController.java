package edu.poniperro.nowait.apps.core.api.controller.interestPlace;

import edu.poniperro.nowait.core.interestPlace.application.createOrDelete.CreateOrDeleteInterestPlaceCommand;
import edu.poniperro.nowait.core.shared.infrastructure.security.JwtTokenProvider;
import edu.poniperro.nowait.shared.domain.DomainError;
import edu.poniperro.nowait.shared.domain.bus.command.CommandBus;
import edu.poniperro.nowait.shared.domain.bus.command.CommandNotRegisteredError;
import edu.poniperro.nowait.shared.domain.bus.query.QueryBus;
import edu.poniperro.nowait.shared.infraestructure.spring.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.HashMap;

@RestController
public final class InterestPlaceCreateDeletePostController extends ApiController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    public InterestPlaceCreateDeletePostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PermitAll
    @PostMapping(path = "/interestPlace/createOrDelete")
    public ResponseEntity index(@RequestBody RequestInterestPlace request) throws CommandNotRegisteredError {
        dispatch(new CreateOrDeleteInterestPlaceCommand(
                jwtTokenProvider.getEmailFromToken(request.getToken()),
                request.getPlaceId()
        ));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}