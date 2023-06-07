package edu.poniperro.nowait.core.comment.comment.application;

import edu.poniperro.nowait.core.comment.comment.domain.Comment;
import edu.poniperro.nowait.shared.domain.bus.query.Response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class CommentResponse implements Response {
    private String commentText;
    private int quantifiableElement;
    private String email;
    private int reports;
    private int likes;
    private int dislikes;
    private String creationDate;
    private String placeId;

    public CommentResponse(String commentText, int quantifiableElement, String email,
                           int reports, int likes, int dislikes, String creationDate, String placeId) {
        this.commentText = commentText;
        this.quantifiableElement = quantifiableElement;
        this.email = email;
        this.reports = reports;
        this.likes = likes;
        this.dislikes = dislikes;
        this.creationDate = creationDate;
        this.placeId = placeId;
    }

    public static CommentResponse fromAggregate(Comment comment) {
        return new CommentResponse(
                comment.getCommentText(),
                comment.getQuantifiableElement(),
                comment.getEmail(),
                comment.getReports(),
                comment.getLikes(),
                comment.getDislikes(),
                comment.getCreationDate(),
                comment.getPlaceId()
        );
    }

    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("commentText", commentText);
            put("quantifiableElement", quantifiableElement);
            put("email", email);
            put("reports", reports);
            put("likes", likes);
            put("dislikes", dislikes);
            put("creationDate", creationDate);
            put("placeId", placeId);
        }};
    }

    public String getCommentText() {
        return commentText;
    }

    public int getQuantifiableElement() {
        return quantifiableElement;
    }

    public String getEmail() {
        return email;
    }

    public int getReports() {
        return reports;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getPlaceId() {
        return placeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentResponse that = (CommentResponse) o;

        if (quantifiableElement != that.quantifiableElement) return false;
        if (reports != that.reports) return false;
        if (likes != that.likes) return false;
        if (dislikes != that.dislikes) return false;
        if (!Objects.equals(commentText, that.commentText)) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(creationDate, that.creationDate)) return false;
        return Objects.equals(placeId, that.placeId);
    }

    @Override
    public int hashCode() {
        int result = commentText != null ? commentText.hashCode() : 0;
        result = 31 * result + quantifiableElement;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + reports;
        result = 31 * result + likes;
        result = 31 * result + dislikes;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (placeId != null ? placeId.hashCode() : 0);
        return result;
    }
}
