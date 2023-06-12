package edu.poniperro.nowait.core.comment.comment.application.create;

public class CreateCommentCommandMother {
    public static CreateCommentCommand create(String commentText, int quantifiableElement, String email, int reports,
            int likes, int dislikes, String creationDate, String placeId) {
        return new CreateCommentCommand(commentText, quantifiableElement, email, reports, likes, dislikes,
                creationDate, placeId);
    }

    public static CreateCommentCommand random() {
        return create("commentText", 1, "email", 1, 1, 1, "creationDate", "placeId");
    }
}
