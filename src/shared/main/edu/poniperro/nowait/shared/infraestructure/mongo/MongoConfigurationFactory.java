package edu.poniperro.nowait.shared.infraestructure.mongo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import edu.poniperro.nowait.shared.domain.Service;
import edu.poniperro.nowait.shared.infraestructure.config.ParameterNotExist;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public final class MongoConfigurationFactory {

    @Value("${database.url}")
    private String DB_URL;
    private final ResourcePatternResolver resourceResolver;


    public MongoConfigurationFactory(ResourcePatternResolver resourceResolver) {
        this.resourceResolver = resourceResolver;
    }

    public MongoDatabase client(
            String host,
            String port,
            String databaseName,
            String user,
            String password,
            String collectionName
    ) throws ParameterNotExist, IOException {

        MongoDatabase database;


        String connectionUrl = String.format("mongodb://%s:%s", host, port);

        if(!host.equals("localhost")){
            connectionUrl = DB_URL;
            //connectionUrl = String.format("mongodb://%s:%s@%s:%s/?retryWrites=true&w=majority", user, password, host, port);
        }

        ConnectionString connectionString = new ConnectionString(connectionUrl);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(databaseName);

        Resource[] resourceCollections = getCollections(collectionName);

        generateCollectionIfNoExists(database, resourceCollections);
        generateIndexIfNoExists(database, resourceCollections);

        return database;
    }

    private Resource[] getCollections(String contextName) throws IOException{
        return resourceResolver.getResources(String.format("classpath:database/%s/*.json", contextName));
    }

    private void generateCollectionIfNoExists(MongoDatabase database, Resource[] resourceCollections) throws IOException{

        for (Resource collection : resourceCollections) {
            String collectionName = Objects.requireNonNull(
                    collection.getFilename()).replace(".json", "");

            if(!collectionExists(collectionName, database)){
                database.createCollection(collectionName);
            }
        }
    }

    private void generateIndexIfNoExists(MongoDatabase database, Resource[] resourceCollections) throws IOException {
        for (Resource resource : resourceCollections) {
            byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String data = new String(bdata, StandardCharsets.UTF_8);
            List<Map<String, Object>> map = new ObjectMapper().readValue(
                    data, new TypeReference<List<Map<String, Object>>>(){});

            MongoCollection<Document> collection = database.getCollection(
                    Objects.requireNonNull(resource.getFilename()).replace(".json", ""));

            for (Map<String, Object> index : map) {
                if (!indexExists((String) index.get("key"), collection)) {
                    IndexOptions options = new IndexOptions();
                    options.name((String) index.get("key"));
                    options.unique((Boolean) index.get("unique"));
                    options.background((Boolean) index.get("background"));
                    collection.createIndex(
                            (Boolean) index.get("ascending")
                                    ?
                                    Indexes.ascending((String) index.get("key"))
                                    :
                                    Indexes.descending((String) index.get("key")),
                            options);
                    //collection.createIndex(new Document(index));
                }
            }
        }
    }

    private boolean indexExists(String indexName, MongoCollection<Document> collection) throws IOException {

        ListIndexesIterable<Document> indexes = collection.listIndexes();

        for (Document document : indexes) {
            if (((Document)document.get("key")).containsKey(indexName)){
                return true;
            }
        }

        return false;
    }

    private boolean collectionExists(String collectionName, MongoDatabase database) throws IOException {

        var collectionNames = database.listCollectionNames();

        for (String name : collectionNames) {
            if (name.equals(collectionName)){
                return true;
            }
        }

        return false;
    }
}
