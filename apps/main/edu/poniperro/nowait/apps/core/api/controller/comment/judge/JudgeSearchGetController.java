package edu.poniperro.nowait.apps.core.api.controller.comment.judge;

import edu.poniperro.nowait.core.comment.judge.application.JudgeResponse;
import edu.poniperro.nowait.core.comment.judge.application.search.SearchJudgeQuery;
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
public class JudgeSearchGetController extends ApiController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public JudgeSearchGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping(path = "/comment/judge/search")
    public HashMap<String, Serializable> index(@RequestBody RequestJudge request) throws CommandNotRegisteredError {

        JudgeResponse response = ask(new SearchJudgeQuery(
                jwtTokenProvider.getEmailFromToken(request.getToken()),
                request.getCommentId()
        ));
        return response.toPrimitives();
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}