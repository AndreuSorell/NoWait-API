package edu.poniperro.nowait.core.profile.user.infraestructure;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.poniperro.nowait.core.profile.user.domain.User;
import edu.poniperro.nowait.core.profile.user.domain.UserRepository;
import edu.poniperro.nowait.shared.domain.Service;
import org.bson.Document;
import org.springframework.context.annotation.Primary;

import java.util.Optional;

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
        public Optional<User> findById(int id) {
                return null;
        }
}