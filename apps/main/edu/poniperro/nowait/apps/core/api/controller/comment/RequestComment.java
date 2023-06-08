package edu.poniperro.nowait.apps.core.api.controller.comment;

public class RequestComment {
    private String id;
    private String token;
    private String commentText;
    private int quantifiableElement;
    private String placeId;

    public String id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}

