package edu.poniperro.nowait.core.comment.judge.application;

import edu.poniperro.nowait.shared.domain.bus.query.Response;

import java.io.Serializable;
import java.util.HashMap;

public class JudgeResponse implements Response {
    private int like;
    private int dislike;
    private int report;

    public JudgeResponse(int like, int dislike, int report) {
        this.like = like;
        this.dislike = dislike;
        this.report = report;
    }

    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("like", like);
            put("dislike", dislike);
            put("report", report);
        }};
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

        JudgeResponse that = (JudgeResponse) o;

        if (like != that.like) return false;
        if (dislike != that.dislike) return false;
        return report == that.report;
    }

    @Override
    public int hashCode() {
        int result = like;
        result = 31 * result + dislike;
        result = 31 * result + report;
        return result;
    }
}
