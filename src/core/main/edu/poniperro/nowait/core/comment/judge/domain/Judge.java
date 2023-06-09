package edu.poniperro.nowait.core.comment.judge.domain;

import java.util.HashMap;

public final class Judge {
    private String email;
    private String commentId;
    private int like;
    private int dislike;
    private int report;
    
    public Judge() {
    }

    public Judge(String email, String commentId, int like, int dislike, int report) {
        this.email = email;
        this.commentId = commentId;
        this.like = like;
        this.dislike = dislike;
        this.report = report;
    }

    public static Judge create(String email, String commentId, int like, int dislike, int report) {
        return new Judge(email, commentId, like, dislike, report);
    }

    public HashMap<String, Object> toPrimitives() {
        return new HashMap<String, Object>() {
            {
                put("email", email);
                put("commentId", commentId);
                put("like", like);
                put("dislike", dislike);
                put("report", report);
            }
        };
    }

    public String getEmail() {
        return email;
    }

    public String getCommentId() {
        return commentId;
    }

    public int getLike() {
        return like;
    }

    public int getDislike() {
        return dislike;
    }

    public int getReport() {
        return report;
    }
}
