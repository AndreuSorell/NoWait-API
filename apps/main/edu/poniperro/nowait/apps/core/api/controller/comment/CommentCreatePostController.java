package edu.poniperro.nowait.apps.core.api.controller.comment;

import edu.poniperro.nowait.core.profile.comment.application.create.CreateCommentCommand;
import edu.poniperro.nowait.shared.domain.DomainError;
import edu.poniperro.nowait.shared.domain.bus.command.CommandBus;
import edu.poniperro.nowait.shared.domain.bus.command.CommandNotRegisteredError;
import edu.poniperro.nowait.shared.domain.bus.query.QueryBus;
import edu.poniperro.nowait.shared.infraestructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
public class CommentCreatePostController extends ApiController {

    public CommentCreatePostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping(path = "/comment/create")
    public ResponseEntity index(@RequestBody RequestComment request) throws CommandNotRegisteredError {
        int reports = 0;
        int likes = 0;
        int dislikes = 0;
        dispatch(new CreateCommentCommand(
                request.getCommentText(),
                request.getQuantifiableElement(),
                request.getEmail(),
                reports,
                likes,
                dislikes,
                LocalDateTime.now().toString(),
                request.getPlaceId()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}

final class RequestComment {
    private String commentText;
    private int quantifiableElement;
    private String email;
    private String placeId;

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getQuantifiableElement() {
        return quantifiableElement;
    }

    public void setQuantifiableElement(int quantifiableElement) {
        this.quantifiableElement = quantifiableElement;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
