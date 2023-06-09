package edu.poniperro.nowait.core.comment.judge.infrastructure;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import edu.poniperro.nowait.core.comment.judge.domain.JudgeRepository;
import edu.poniperro.nowait.shared.domain.Service;
import org.springframework.context.annotation.Primary;

import  edu.poniperro.nowait.core.comment.judge.domain.Judge;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

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
        // Se verifica si ya existe un documento con el mismo email y commentId
        Bson filter = and(eq("email", judge.getEmail()), eq("commentId", judge.getCommentId()));
        Document existingDocument = judgeCollection.find(filter).first();
        if (existingDocument != null) {
            return;
        }
        judgeCollection.insertOne(new Document(judge.toPrimitives()));
    }

   /* @Override
    public void update(String email, String commentId, int like, int dislike, int report) {
        MongoCollection<Document> judgeCollection = database.getCollection("judge");
        // Se verifica si ya existe un documento con el mismo email y commentId
        Bson filter = and(eq("email", email), eq("commentId", commentId));
        Document existingDocument = judgeCollection.find(filter).first();
        if (existingDocument == null) {
            return;
        }
        Document newDocument = new Document();
        newDocument.append("$set", new Document().append("like", like).append("dislike", dislike).append("report", report));
        judgeCollection.updateOne(existingDocument, newDocument);
    }*/

    @Override
    public boolean update(String email, String commentId, int like, int dislike, int report) {
        MongoCollection<Document> judgeCollection = database.getCollection("judge");
        // Filtrar el documento por email y commentId
        Bson filter = and(eq("email", email), eq("commentId", commentId));
        Bson update = Updates.set("like", like);
        update = Updates.combine(update, Updates.set("dislike", dislike));
        update = Updates.combine(update, Updates.set("report", report));
        UpdateResult result = judgeCollection.updateOne(filter, update);
        // Verificar si se realizó la actualización correctamente
        if (result.getModifiedCount() != 1) {
            System.out.println("Error al actualizar el documento");
            return false;
        } else {
            return true;
        }
    }


}
