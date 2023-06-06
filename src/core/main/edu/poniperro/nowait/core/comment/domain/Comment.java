package edu.poniperro.nowait.core.comment.domain;

import java.util.HashMap;
import java.util.Objects;

public final class Comment {
    private String commentText;
    private int quantifiableElement;
    private String email;
    private int reports;
    private int likes;
    private int dislikes;
    private String creationDate;
    private String placeId;

    private Comment(String commentText, int quantifiableElement, String email, int reports, int likes, int dislikes,
            String creationDate, String placeId) {
        this.commentText = commentText;
        this.quantifiableElement = quantifiableElement;
        this.email = email;
        this.reports = reports;
        this.likes = likes;
        this.dislikes = dislikes;
        this.creationDate = creationDate;
        this.placeId = placeId;
    }

    public static Comment create(String commentText, int quantifiableElement, String email, int reports, int likes,
            int dislikes, String creationDate, String placeId) {
        return new Comment(commentText, quantifiableElement, email, reports, likes, dislikes, creationDate, placeId);
    }

    public HashMap<String, Object> toPrimitives() {
        return new HashMap<String, Object>() {
            {
                put("commentText", commentText);
                put("quantifiableElement", quantifiableElement);
                put("email", email);
                put("reports", reports);
                put("likes", likes);
                put("dislikes", dislikes);
                put("creationDate", creationDate);
                put("placeId", placeId);
            }
        };
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getReports() {
        return reports;
    }

    public void setReports(int reports) {
        this.reports = reports;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Comment comment = (Comment) o;

        if (quantifiableElement != comment.quantifiableElement)
            return false;
        if (reports != comment.reports)
            return false;
        if (likes != comment.likes)
            return false;
        if (dislikes != comment.dislikes)
            return false;
        if (!Objects.equals(commentText, comment.commentText))
            return false;
        if (!Objects.equals(email, comment.email))
            return false;
        if (!Objects.equals(creationDate, comment.creationDate))
            return false;
        return Objects.equals(placeId, comment.placeId);
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
