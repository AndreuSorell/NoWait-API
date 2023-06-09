package edu.poniperro.nowait.core.comment.judge.infrastructure;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.poniperro.nowait.core.comment.judge.domain.JudgeRepository;
import edu.poniperro.nowait.shared.domain.Service;
import org.springframework.context.annotation.Primary;

import  edu.poniperro.nowait.core.comment.judge.domain.Judge;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

@Service
@Primary
public class MongoDBJudgeRepository implements JudgeRepository {
    private final MongoDatabase database;

    public MongoDBJudgeRepository(MongoDatabase database) {
        this.database = database;
    }

    @Override
    public void save(Judge judge) {
        MongoCollection<Document> judgeCollection = database.getCollection("judge");
        // Verificar si ya existe un documento con el mismo email y commentId
        Bson filter = and(eq("email", judge.getEmail()), eq("commentId", judge.getCommentId()));
        Document existingDocument = judgeCollection.find(filter).first();
        if (existingDocument != null) {
            return;
        }
        judgeCollection.insertOne(new Document(judge.toPrimitives()));
    }

}
