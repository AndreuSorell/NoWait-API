package edu.poniperro.nowait.apps.core.api.controller.comment.concurrence;

import edu.poniperro.nowait.core.comment.concurrence.ConcurrenceResponse;
import edu.poniperro.nowait.core.comment.concurrence.application.ConcurrenceQuery;
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
public class ConcurrenceCalculatePostController extends ApiController {

    public ConcurrenceCalculatePostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping(path = "/concurrence")
    public HashMap<String, Serializable> index(@RequestBody RequestConcurrence request) {
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

