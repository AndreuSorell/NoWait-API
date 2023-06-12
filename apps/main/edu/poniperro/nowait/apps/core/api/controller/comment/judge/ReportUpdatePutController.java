package edu.poniperro.nowait.apps.core.api.controller.comment.judge;

import edu.poniperro.nowait.core.comment.judge.application.update.UpdateJudgeCommand;
import edu.poniperro.nowait.core.shared.infrastructure.security.JwtTokenProvider;
import edu.poniperro.nowait.shared.domain.DomainError;
import edu.poniperro.nowait.shared.domain.bus.command.CommandBus;
import edu.poniperro.nowait.shared.domain.bus.query.QueryBus;
import edu.poniperro.nowait.shared.infraestructure.spring.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public final class ReportUpdatePutController extends ApiController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public ReportUpdatePutController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PutMapping(path = "/comment/report/update")
    public ResponseEntity index(@RequestBody RequestJudge request){
        jwtTokenProvider.getEmailFromToken(request.getToken());
        dispatch(new UpdateJudgeCommand(
                jwtTokenProvider.getEmailFromToken(request.getToken()),
                request.getCommentId(),
                0,
                1,
                1
        ));

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
