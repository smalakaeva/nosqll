package com.okmich.mysql2mongodb.migrate;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

public class TagsByGenre extends BaseMigration {
    public TagsByGenre(String dbServerUrl, String dbUser, String dbPassword,
                       String mongoDbUrl, String mongoDbName) {
        super(dbServerUrl, dbUser, dbPassword, mongoDbUrl, mongoDbName);
    }

    @Override
    public void migrate() {
        // Реализация миграции данных
    }

    @Override
    protected Document rowToDocument(Object... row) {
        return new Document("data", row);
    }

    @Override
    public String getDataFromMongo(String selectedItem) {
        MongoDatabase mongoDatabase = getMongoDatabase(mongoDbUrl, mongoDbName);
        MongoCollection<Document> collection = mongoDatabase.getCollection("tags");
        StringBuilder result = new StringBuilder();
        FindIterable<Document> iterable = collection.find();

        for (Document document : iterable) {
            if (document.containsKey("movie") && document.get("movie") instanceof Document) {
                Document movie = (Document) document.get("movie");
                if (movie.containsKey("title")) {
                    result.append(movie.getString("title")).append("\n");
                }
            }
        }
        return result.toString();
    }
}

