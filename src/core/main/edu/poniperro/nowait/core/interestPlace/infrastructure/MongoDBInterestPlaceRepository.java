package edu.poniperro.nowait.core.interestPlace.infrastructure;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.poniperro.nowait.core.interestPlace.domain.InterestPlace;
import edu.poniperro.nowait.core.interestPlace.domain.InterestPlaceRepository;
import edu.poniperro.nowait.shared.domain.Service;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.context.annotation.Primary;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

@Service
@Primary
public class MongoDBInterestPlaceRepository implements InterestPlaceRepository {
    private final MongoDatabase database;

    public MongoDBInterestPlaceRepository(MongoDatabase database) {
        this.database = database;
    }

    @Override
    public void save(InterestPlace interestPlace) {
        MongoCollection<Document> interestPlaceCollection = database.getCollection("interestPlace");
        // Se verifica si ya existe un documento con el mismo email y placeId
        Bson filter = and(eq("email", interestPlace.getEmail()), eq("placeId", interestPlace.getPlaceId()));
        Document existingDocument = interestPlaceCollection.find(filter).first();
        if (existingDocument != null) {
            return;
        }
        interestPlaceCollection.insertOne(new Document(interestPlace.toPrimitives()));
    }

    @Override
    public InterestPlace findByEmailAndPlaceId(String email, String placeId) {
        MongoCollection<Document> interestPlaceCollection = database.getCollection("interestPlace");
        Bson filter = and(eq("email", email), eq("placeId", placeId));
        Document document = interestPlaceCollection.find(filter).first();
        if (document == null) {
            return null;
        }
        return InterestPlace.fromPrimitives(
                document.getString("email"),
                document.getString("placeId")
        );
    }
}
