package edu.poniperro.nowait.core.profile.comment.infrastructure;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.poniperro.nowait.core.profile.comment.domain.Comment;
import edu.poniperro.nowait.core.profile.comment.domain.CommentRepository;
import edu.poniperro.nowait.shared.domain.Service;
import org.bson.Document;
import org.springframework.context.annotation.Primary;

@Service
@Primary
public class MongoDBCommentRepository implements CommentRepository {

    private final MongoDatabase database;

    public MongoDBCommentRepository(MongoDatabase database) {
        this.database = database;
    }

    @Override
    public void save(Comment comment) {
        MongoCollection<Document> commentCollection = database.getCollection("comment");
        commentCollection.insertOne(new Document(comment.toPrimitives()));
    }
}
