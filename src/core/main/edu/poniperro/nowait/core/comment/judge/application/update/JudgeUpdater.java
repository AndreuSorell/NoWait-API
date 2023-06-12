package edu.poniperro.nowait.core.comment.judge.application.update;

import edu.poniperro.nowait.core.comment.comment.domain.Comment;
import edu.poniperro.nowait.core.comment.comment.domain.CommentRepository;
import edu.poniperro.nowait.core.comment.judge.domain.Judge;
import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.core.comment.judge.domain.JudgeRepository;

import java.util.HashMap;

@Service
public final class JudgeUpdater {
    private final JudgeRepository repository;
    private final CommentRepository commentRepository;

    public JudgeUpdater(JudgeRepository repository, CommentRepository commentRepository) {
        this.repository = repository;
        this.commentRepository = commentRepository;
    }

    public void update(String email, String commentId, int like, int dislike, int report) {
        Judge judge = repository.findByEmailAndCommentId(email, commentId);

        boolean hasLike = judge != null && judge.getLike() == 1;
        boolean hasDislike = judge != null && judge.getDislike() == 1;
        boolean hasReport = judge != null && judge.getReport() == 1;

        if (isJudgeOpposite(hasLike, hasDislike, hasReport, like, dislike, report)) {
            if (like == 1 && hasLike) {
                like = 0;
            } else if (dislike == 1 && report == 0 && hasDislike) {
                dislike = 0;
                report = hasReport ? 1 : 0;
            } else if (report == 1 && hasReport) {
                dislike = hasDislike ? 1 : 0;
                report = 0;
            }
            repository.update(email, commentId, like, dislike, report);
            commentJudgeManipulatorOpposite(like, dislike, report, commentId, hasLike, hasDislike, hasReport);
        } else {
            if (repository.update(email, commentId, like, dislike, report)) {
                commentJudgeManipulator(like, dislike, report, commentId, hasLike, hasDislike, hasReport);
            } else {
                repository.save(Judge.create(email, commentId, like, dislike, report));
                repository.update(email, commentId, like, dislike, report);
                commentJudgeManipulator(like, dislike, report, commentId, hasLike, hasDislike, hasReport);
            }
        }
    }

    public boolean isJudgeOpposite(boolean hasLike, boolean hasDislike, boolean hasReport, int like, int dislike, int report) {
        return (like == 1 && hasLike) || (dislike == 1 && report == 0 && hasDislike) || (report == 1 && hasReport);
    }

    public void commentJudgeManipulator(int like, int dislike, int report, String commentId, boolean hasLike, boolean hasDislike, boolean hasReport) {
        if (like == 1) {
            updateLikes(commentId, hasLike);
            if (hasDislike) {
                updateDislikesOpposite(commentId);
            }
        } else if (dislike == 1 && report == 0) {
            updateDislikes(commentId, hasDislike);
            if (hasLike) {
                updateLikesOpposite(commentId);
            }
        } else if (report == 1) {
            updateDislikes(commentId, hasDislike);
            updateReports(commentId, hasReport);
            if (hasLike) {
                updateLikesOpposite(commentId);
            }
        }
    }

    public void updateLikes(String commentId, boolean hasLike) {
        if (!hasLike) {
            Comment comment = commentRepository.findById(commentId);
            commentRepository.updateJudge(commentId, comment.getLikes() + 1, comment.getDislikes(), comment.getReports());
        }
    }

    public void updateDislikes(String commentId, boolean hasDislike) {
        if (!hasDislike) {
            Comment comment = commentRepository.findById(commentId);
            commentRepository.updateJudge(commentId, comment.getLikes(), comment.getDislikes() + 1, comment.getReports());
        }
    }

    public void updateReports(String commentId, boolean hasReport) {
        if (!hasReport) {
            Comment comment = commentRepository.findById(commentId);
            int reports = comment.getReports();
            if (reports == 3) {
                commentRepository.delete(commentId);
            } else {
                commentRepository.updateJudge(commentId, comment.getLikes(), comment.getDislikes(), reports + 1);
            }
        }
    }

    public void commentJudgeManipulatorOpposite (int like, int dislike, int report, String commentId, boolean hasLike, boolean hasDislike, boolean hasReport) {
        if (like == 0 && hasLike) {
            updateLikesOpposite(commentId);
            if (hasDislike) {
                updateDislikesOpposite(commentId);
            }
        } else if (dislike == 0 && hasDislike) {
            updateDislikesOpposite(commentId);
            if (hasLike) {
                updateLikesOpposite(commentId);
            }
        } else if (report == 0 && hasReport) {
            updateReportsOpposite(commentId);
        }
    }

    public void updateLikesOpposite(String commentId) {
        Comment comment = commentRepository.findById(commentId);
        commentRepository.updateJudge(commentId, comment.getLikes() - 1, comment.getDislikes(), comment.getReports());

    }

    public void updateDislikesOpposite(String commentId) {
        Comment comment = commentRepository.findById(commentId);
        commentRepository.updateJudge(commentId, comment.getLikes(), comment.getDislikes() - 1, comment.getReports());
    }

    public void updateReportsOpposite(String commentId) {
        Comment comment = commentRepository.findById(commentId);
        commentRepository.updateJudge(commentId, comment.getLikes(), comment.getDislikes(), comment.getReports() - 1);
    }
}
