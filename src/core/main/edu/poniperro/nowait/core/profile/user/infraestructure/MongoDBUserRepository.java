package edu.poniperro.nowait.core.profile.user.infraestructure;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.poniperro.nowait.core.profile.user.domain.User;
import edu.poniperro.nowait.core.profile.user.domain.UserRepository;
import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.domain.Utils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.context.annotation.Primary;

import java.util.Optional;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

@Service
@Primary
public class MongoDBUserRepository implements UserRepository {

        private final MongoDatabase database;

        public MongoDBUserRepository(MongoDatabase database) { this.database = database; }

        @Override
        public void save(User user) {
                MongoCollection<Document> userCollection = database.getCollection("user");
                userCollection.insertOne(new Document(user.toPrimitives()));
        }

        @Override
        public User findByEmail(String email){
                /*MongoCollection<Document> userCollection = database.getCollection("user");
                Document user = userCollection.find(new Document("email", email)).first();
                if (user == null) {
                        return Optional.empty();
                }
                return Optional.of(User.fromPrimitives(Utils.jsonDecode(user.toJson())
                ));*/

                MongoCollection<Document> userCollection = database.getCollection("user");
                Bson filter = and(eq("email", email));
                return User.fromPrimitives(Utils.jsonDecode(userCollection.find(filter).first().toJson()));
        }

        @Override
        public Optional<User> findByEmailAndPassword(String email, String password) {
                MongoCollection<Document> userCollection = database.getCollection("user");
                Bson filter = and(eq("email", email), eq("password", password));
                return Optional.ofNullable(userCollection.find(filter).first())
                        .map(document -> User.fromPrimitives(Utils.jsonDecode(document.toJson())));
        }

}