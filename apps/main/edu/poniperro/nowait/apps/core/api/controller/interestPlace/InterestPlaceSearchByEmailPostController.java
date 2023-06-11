package edu.poniperro.nowait.apps.core.api.controller.interestPlace;

import edu.poniperro.nowait.core.interestPlace.application.InterestPlacesResponse;
import edu.poniperro.nowait.core.interestPlace.application.InterestPlaceResponse;
import edu.poniperro.nowait.core.interestPlace.application.search.SearchInterestPlaceByEmailQuery;
import edu.poniperro.nowait.core.shared.infrastructure.security.JwtTokenProvider;
import edu.poniperro.nowait.shared.domain.DomainError;
import edu.poniperro.nowait.shared.domain.bus.command.CommandBus;
import edu.poniperro.nowait.shared.domain.bus.query.QueryBus;
import edu.poniperro.nowait.shared.infraestructure.spring.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class InterestPlaceSearchByEmailPostController extends ApiController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public InterestPlaceSearchByEmailPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping(path = "/interestPlace/searchByEmail")
    public List<HashMap<String, Serializable>> index(@RequestBody RequestInterestPlace request) {

        InterestPlacesResponse responses = ask(new SearchInterestPlaceByEmailQuery(
                jwtTokenProvider.getEmailFromToken(request.getToken())
        ));
        return responses.getInterestPlaces().stream().map(InterestPlaceResponse::toPrimitives).collect(Collectors.toList());
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}