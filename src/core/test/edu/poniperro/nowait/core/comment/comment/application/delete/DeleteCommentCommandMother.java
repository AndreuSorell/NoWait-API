package edu.poniperro.nowait.core.comment.comment.application.delete;

public class DeleteCommentCommandMother {
    public static DeleteCommentCommand create(String id) {
        return new DeleteCommentCommand(id);
    }

    public static DeleteCommentCommand random() {
        return create("id");
    }
}
