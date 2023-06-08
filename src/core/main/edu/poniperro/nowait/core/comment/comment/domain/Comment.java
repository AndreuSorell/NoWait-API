package edu.poniperro.nowait.core.comment.comment.domain;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.HashMap;

public final class Comment {
    private String id;
    private String commentText;
    private int quantifiableElement;
    private String email;
    private int reports;
    private int likes;
    private int dislikes;
    private String creationDate;
    private String placeId;

    public Comment() {
    }

    public Comment(String id, String commentText, int quantifiableElement, String email, int reports, int likes, int dislikes, String creationDate, String placeId) {
        this.id = id;
        this.commentText = commentText;
        this.quantifiableElement = quantifiableElement;
        this.email = email;
        this.reports = reports;
        this.likes = likes;
        this.dislikes = dislikes;
        this.creationDate = creationDate;
        this.placeId = placeId;
    }

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

    public static Comment fromPrimitives(String id, HashMap<String, Serializable> data) {
        return new Comment(
                id,
                (String) data.get("commentText"),
                (int) data.get("quantifiableElement"),
                (String) data.get("email"),
                (int) data.get("reports"),
                (int) data.get("likes"),
                (int) data.get("dislikes"),
                (String) data.get("creationDate"),
                (String) data.get("placeId")
        );
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
}
