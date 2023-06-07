package edu.poniperro.nowait.apps.core.api.controller.concurrence;

import edu.poniperro.nowait.core.comment.comment.application.CommentResponse;
import edu.poniperro.nowait.core.comment.concurrence.ConcurrenceResponse;
import edu.poniperro.nowait.core.comment.concurrence.application.ConcurrenceQuery;
import edu.poniperro.nowait.shared.domain.DomainError;
import edu.poniperro.nowait.shared.domain.bus.command.CommandBus;
import edu.poniperro.nowait.shared.domain.bus.command.CommandNotRegisteredError;
import edu.poniperro.nowait.shared.domain.bus.query.QueryBus;
import edu.poniperro.nowait.shared.infraestructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ConcurrenceGetController extends ApiController {

    public ConcurrenceGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping(path = "/concurrence")
    public HashMap<String, Serializable> index(@RequestBody RequestConcurrence request) throws CommandNotRegisteredError {

        ConcurrenceResponse response = ask(new ConcurrenceQuery(
                request.getPlaceId()
        ));
        return response.toPrimitives();
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}

final class RequestConcurrence {
    private String placeId;

    public RequestConcurrence() {
    }

    public RequestConcurrence(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceId() {
        return placeId;
    }
}

