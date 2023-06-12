package edu.poniperro.nowait.core.interestPlace.infrastructure;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.poniperro.nowait.core.interestPlace.domain.InterestPlace;
import edu.poniperro.nowait.core.interestPlace.domain.InterestPlaceRepository;
import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.Utils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

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
    public boolean save(InterestPlace interestPlace) {
        MongoCollection<Document> interestPlaceCollection = database.getCollection("interestPlace");
        // Se verifica si ya existe un documento con el mismo email y placeId
        Bson filter = and(eq("email", interestPlace.getEmail()), eq("placeId", interestPlace.getPlaceId()));
        Document existingDocument = interestPlaceCollection.find(filter).first();
        if (existingDocument != null) {
            return false;
        }
        interestPlaceCollection.insertOne(new Document(interestPlace.toPrimitives()));
        return true;
    }

    @Override
    public InterestPlace searchByEmailAndPlaceId(String email, String placeId) {
        MongoCollection<Document> interestPlaceCollection = database.getCollection("interestPlace");
        Bson filter = and(eq("email", email), eq("placeId", placeId));
        Document document = interestPlaceCollection.find(filter).first();
        if (document == null) {
            return null;
        }
        return InterestPlace.fromPrimitives(Utils.jsonDecode(document.toJson())
        );
    }

    @Override
    public void deleteByEmailAndPlaceId(String email, String placeId) {
        MongoCollection<Document> interestPlaceCollection = database.getCollection("interestPlace");
        Bson filter = and(eq("email", email), eq("placeId", placeId));
        interestPlaceCollection.deleteOne(filter);
    }

    @Override
    public List<InterestPlace> searchByEmail(String email) {
        MongoCollection<Document> interestPlaceCollection = database.getCollection("interestPlace");
        Bson filter = eq("email", email);
        List<Document> interestPlaceDocuments = interestPlaceCollection.find(filter).into(new ArrayList<>());
        List<InterestPlace> interestPlaces = new ArrayList<>();
        for (Document doc : interestPlaceDocuments) {
            InterestPlace interestPlace = InterestPlace.fromPrimitives(Utils.jsonDecode(doc.toJson()));
            interestPlaces.add(interestPlace);
        }
        return interestPlaces;
    }
}
