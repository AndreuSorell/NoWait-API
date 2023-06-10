package edu.poniperro.nowait.apps.core.api.controller.comment.judge;

public class RequestJudge {
        private String token;
        private String commentId;
        private int like;
        private int dislike;
        private int report;

        public RequestJudge() {
        }

        public RequestJudge(String token, String commentId, int like, int dislike, int report) {
            this.token = token;
            this.commentId = commentId;
            this.like = like;
            this.dislike = dislike;
            this.report = report;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
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
}