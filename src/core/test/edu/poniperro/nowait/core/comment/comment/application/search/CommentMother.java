package edu.poniperro.nowait.core.comment.comment.application.search;

import edu.poniperro.nowait.core.comment.comment.domain.Comment;
import edu.poniperro.nowait.shared.domain.MotherCreator;

import java.util.ArrayList;
import java.util.List;

public class CommentMother {
    public static List<Comment> randomList(int i) {
        List<Comment> comments = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            comments.add(random());
        }
        return comments;
    }

    public static Comment random() {
        return Comment.create(
                MotherCreator.random().name().name(),
                MotherCreator.random().number().randomDigit(),
                MotherCreator.random().internet().emailAddress(),
                MotherCreator.random().number().randomDigit(),
                MotherCreator.random().number().randomDigit(),
                MotherCreator.random().number().randomDigit(),
                MotherCreator.random().date().toString(),
                MotherCreator.random().name().firstName()
        );
    }

    public static List<Comment> randomListWithPlaceId(int i, String placeId) {
        List<Comment> comments = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            comments.add(randomWithPlaceId(placeId));
        }
        return comments;
    }

    private static Comment randomWithPlaceId(String placeId) {
        return Comment.create(
                MotherCreator.random().name().name(),
                MotherCreator.random().number().randomDigit(),
                MotherCreator.random().internet().emailAddress(),
                MotherCreator.random().number().randomDigit(),
                MotherCreator.random().number().randomDigit(),
                MotherCreator.random().number().randomDigit(),
                MotherCreator.random().date().toString(),
                placeId
        );
    }
}
