package edu.poniperro.nowait.apps.core.api.controller.comment.judge;

import edu.poniperro.nowait.apps.core.api.controller.comment.RequestComment;
import edu.poniperro.nowait.core.comment.judge.application.CreateJudgeCommand;
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

import java.time.LocalDateTime;
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
        int reports = 0;
        int likes = 0;
        int dislikes = 0;
        dispatch(new CreateJudgeCommand(
                jwtTokenProvider.getEmailFromToken(request.getToken()),
                request.getCommentId(),
                reports,
                likes,
                dislikes
        ));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}

final class RequestJudge {
    private String token;
    private String commentId;
    private String like;
    private String dislike;
    private String report;

    public RequestJudge() {
    }

    public RequestJudge(String token, String commentId, String like, String dislike, String report) {
        this.token = token;
        this.commentId = commentId;
        this.like = like;
        this.dislike = dislike;
        this.report = report;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}