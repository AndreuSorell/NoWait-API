package edu.poniperro.nowait.core.comment.judge.application.update;

import edu.poniperro.nowait.shared.domain.bus.command.Command;

import java.util.Objects;

public class UpdateJudgeCommand implements Command {
    private String email;
    private String commentId;
    private int like;
    private int dislike;
    private int report;

    public UpdateJudgeCommand(String email, String commentId, int like, int dislike, int report) {
        this.email = email;
        this.commentId = commentId;
        this.like = like;
        this.dislike = dislike;
        this.report = report;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateJudgeCommand that = (UpdateJudgeCommand) o;

        if (like != that.like) return false;
        if (dislike != that.dislike) return false;
        if (report != that.report) return false;
        if (!Objects.equals(email, that.email)) return false;
        return Objects.equals(commentId, that.commentId);
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (commentId != null ? commentId.hashCode() : 0);
        result = 31 * result + like;
        result = 31 * result + dislike;
        result = 31 * result + report;
        return result;
    }
}
