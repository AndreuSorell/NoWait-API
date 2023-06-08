package edu.poniperro.nowait.core.comment.comment.infrastructure;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.poniperro.nowait.core.comment.comment.domain.Comment;
import edu.poniperro.nowait.core.comment.comment.domain.CommentRepository;
import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.Utils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

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

    @Override
    public List<Comment> searchByEmailAndPlaceId(String email, String placeId) {
        MongoCollection<Document> commentCollection = database.getCollection("comment");
        // find all comments from a place and email
        Bson filter = and(eq("email", email), eq("placeId", placeId));
        List<Document> commentDocuments = commentCollection.find(filter).into(new ArrayList<>());
        List<Comment> comments = new ArrayList<>();
        for (Document doc : commentDocuments) {
            ObjectId id = doc.getObjectId("_id");
            Comment comment = Comment.fromPrimitives(id.toString(), Utils.jsonDecode(doc.toJson()));
            comments.add(comment);
        }
        return comments;
    }


    @Override
    public List<Comment> searchByPlaceId(String placeId) {
        MongoCollection<Document> commentCollection = database.getCollection("comment");
        // find all comments from a place
        Bson filter = eq("placeId", placeId);
        List<Document> commentDocuments = commentCollection.find(filter).into(new ArrayList<>());
        List<Comment> comments = new ArrayList<>();
        for (Document doc : commentDocuments) {
            ObjectId id = doc.getObjectId("_id");
            Comment comment = Comment.fromPrimitives(id.toString(), Utils.jsonDecode(doc.toJson()));
            comments.add(comment);
        }
        return comments;
    }

    @Override
    public Comment findById(String id) {
        MongoCollection<Document> commentCollection = database.getCollection("comment");
        Document comment = commentCollection.find(new Document("_id", new ObjectId(id))).first();
        if (comment == null) {
            return new Comment();
        }
        return Comment.fromPrimitives(id, Utils.jsonDecode(comment.toJson()));
    }
}
