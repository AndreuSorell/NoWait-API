package edu.poniperro.nowait.apps.core.api.controller.comment.judge;

import edu.poniperro.nowait.core.comment.judge.application.create.CreateJudgeCommand;
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

import java.util.HashMap;

@RestController
public class JudgeCreatePostController extends ApiController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    public JudgeCreatePostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping(path = "/comment/judge/create")
    public ResponseEntity index(@RequestBody RequestJudge request) throws CommandNotRegisteredError {
        dispatch(new CreateJudgeCommand(
                jwtTokenProvider.getEmailFromToken(request.getToken()),
                request.getCommentId(),
                request.getLike(),
                request.getDislike(),
                request.getReport()
        ));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}