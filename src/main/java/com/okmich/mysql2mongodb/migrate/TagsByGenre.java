package com.okmich.mysql2mongodb.migrate;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class TagsByGenre extends BaseMigration {
    public TagsByGenre(String dbServerUrl, String dbUser, String dbPassword,
                       String mongoDbUrl, String mongoDbName) {
        super(dbServerUrl, dbUser, dbPassword, mongoDbUrl, mongoDbName);
    }


    @Override
    public void migrate() {

    }

    @Override
    protected Document rowToDocument(Object... row) {
        return null;
    }

    @Override
    public String getDataFromMongo(String selectedItem) {
        MongoDatabase mongoDatabase = getMongoDatabase(mongoDbUrl, mongoDbName);
        MongoCollection<Document> collection = mongoDatabase.getCollection("tags");
        StringBuilder result = new StringBuilder();
        FindIterable<Document> iterable = collection.find();

        for (Document document : iterable) {
            result.append(document.toJson()).append("\n");
        }
        return result.toString();

    }
}
