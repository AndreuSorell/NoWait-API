package edu.poniperro.nowait.apps.core.api.controller.comment;

import edu.poniperro.nowait.core.comment.comment.application.create.CreateCommentCommand;
import edu.poniperro.nowait.core.comment.comment.application.delete.DeleteCommentCommand;
import edu.poniperro.nowait.core.shared.infrastructure.security.JwtTokenProvider;
import edu.poniperro.nowait.shared.domain.DomainError;
import edu.poniperro.nowait.shared.domain.bus.command.CommandBus;
import edu.poniperro.nowait.shared.domain.bus.query.QueryBus;
import edu.poniperro.nowait.shared.infraestructure.spring.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
public class CommentDeleteController extends ApiController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    public CommentDeleteController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @DeleteMapping(path = "/comment/delete")
    public ResponseEntity index(@RequestBody RequestComment request) {
        jwtTokenProvider.getEmailFromToken(request.getToken());
        dispatch(new DeleteCommentCommand(request.id()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
