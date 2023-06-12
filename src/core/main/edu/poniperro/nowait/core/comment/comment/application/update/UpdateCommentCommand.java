package edu.poniperro.nowait.core.comment.comment.application.update;

import edu.poniperro.nowait.shared.domain.bus.command.Command;

import java.util.Objects;

public class UpdateCommentCommand implements Command {
    private String id;
    private String commentText;
    private int quantifiableElement;
    private String creationDate;

    public UpdateCommentCommand(String id, String commentText, int quantifiableElement, String creationDate) {
        this.id = id;
        this.commentText = commentText;
        this.quantifiableElement = quantifiableElement;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateCommentCommand that = (UpdateCommentCommand) o;

        if (quantifiableElement != that.quantifiableElement) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(commentText, that.commentText)) return false;
        return Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        result = 31 * result + quantifiableElement;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }
}
