package edu.poniperro.nowait.apps.core.api.controller.comment.concurrence;

import edu.poniperro.nowait.core.comment.concurrence.ConcurrencePerDayResponse;
import edu.poniperro.nowait.core.comment.concurrence.application.ConcurrencePerDayQuery;
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
public class ConcurrenceCalculatePerDayPostController extends ApiController {

    public ConcurrenceCalculatePerDayPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping(path = "/concurrence/perDay")
    public HashMap<String, Serializable> index(@RequestBody RequestConcurrence request) {
        ConcurrencePerDayResponse response = ask(new ConcurrencePerDayQuery(
                request.getPlaceId(),
                request.getDay()
        ));
        return response.toPrimitives();
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}

