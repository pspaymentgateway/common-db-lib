package paysecure.common.library;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class MultipleMongoConfig {

    // 👉 Primary Mongo Properties
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.data.mongodb")
    public MongoProperties primaryMongoProperties() {
        return new MongoProperties();
    }

    // 👉 Secondary Mongo Properties
    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.secondary")
    public MongoProperties secondaryMongoProperties() {
        return new MongoProperties();
    }

    // 👉 Primary MongoClient
    @Bean
    @Primary
    public MongoClient primaryMongoClient(@Qualifier("primaryMongoProperties") MongoProperties mongoProperties) {
        return MongoClients.create(mongoProperties.getUri());
    }

    // 👉 Secondary MongoClient
    @Bean
    public MongoClient secondaryMongoClient(@Qualifier("secondaryMongoProperties") MongoProperties mongoProperties) {
        return MongoClients.create(mongoProperties.getUri());
    }

    // 👉 Primary MongoTemplate
    @Bean
    @Primary
    public MongoTemplate primaryMongoTemplate(@Qualifier("primaryMongoClient") MongoClient mongoClient,
                                              @Qualifier("primaryMongoProperties") MongoProperties mongoProperties) {
        return new MongoTemplate(mongoClient, mongoProperties.getDatabase());
    }

    // 👉 Secondary MongoTemplate
    @Bean
    public MongoTemplate secondaryMongoTemplate(@Qualifier("secondaryMongoClient") MongoClient mongoClient,
                                                @Qualifier("secondaryMongoProperties") MongoProperties mongoProperties) {
        return new MongoTemplate(mongoClient, mongoProperties.getDatabase());
    }
}


