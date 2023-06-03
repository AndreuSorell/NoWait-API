package edu.poniperro.nowait.core.shared.infrastructure.persistance;

import com.mongodb.client.MongoDatabase;
import edu.poniperro.nowait.shared.infraestructure.config.ParameterNotExist;
import edu.poniperro.nowait.shared.infraestructure.mongo.MongoConfigurationFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class CoreMongoDbConfiguration {
    private final MongoConfigurationFactory factory;

    public CoreMongoDbConfiguration(MongoConfigurationFactory factory) {
        this.factory = factory;
    }

    //mongodb://localhost:27017/
    @Bean
    public MongoDatabase cLient() throws ParameterNotExist, IOException {
        return factory.client(
                "localhost",
                "27017",
                "nowait",
                "asorellpou",
                "asorellpou",
                "core");
    }
}
