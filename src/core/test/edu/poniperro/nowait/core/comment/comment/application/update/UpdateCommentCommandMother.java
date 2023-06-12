package edu.poniperro.nowait.core.comment.comment.application.update;

public class UpdateCommentCommandMother {
    public static UpdateCommentCommand create(String id, String commentText, int quantifiableElement, String creationDate) {
        return new UpdateCommentCommand(id, commentText, quantifiableElement, creationDate);
    }

    public static UpdateCommentCommand random() {
        return create("id", "commentText", 1, "creationDate");
    }
}
