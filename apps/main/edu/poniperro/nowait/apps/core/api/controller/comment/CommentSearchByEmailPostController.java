package edu.poniperro.nowait.apps.core.api.controller.comment;

import edu.poniperro.nowait.core.comment.comment.application.CommentResponse;
import edu.poniperro.nowait.core.comment.comment.application.CommentsResponse;
import edu.poniperro.nowait.core.comment.comment.application.search.SearchCommentsByEmailQuery;
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
public class CommentSearchByEmailPostController extends ApiController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    public CommentSearchByEmailPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping(path = "/comments/searchByEmail")
    public List<HashMap<String, Serializable>> index(@RequestBody RequestComment request) {

        CommentsResponse responses = ask(new SearchCommentsByEmailQuery(
                jwtTokenProvider.getEmailFromToken(request.getToken())
        ));
        return responses.getComments().stream().map(CommentResponse::toPrimitives).collect(Collectors.toList());
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}