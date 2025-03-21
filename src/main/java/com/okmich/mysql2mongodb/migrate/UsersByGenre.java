package com.okmich.mysql2mongodb.migrate;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

public class UsersByGenre extends BaseMigration {
    /**
     *
     * @param dbServerUrl
     * @param dbUser
     * @param dbPassword
     * @param mongoDbUrl
     * @param mongoDbName
     */
    public UsersByGenre(String dbServerUrl,
                        String dbUser, String dbPassword,
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

    public String getDataFromMongo(String selectedItem) {

        MongoDatabase mongoDB = getMongoDatabase(mongoDbUrl, mongoDbName);
        MongoCollection<Document> collection = mongoDB.getCollection("users");
        StringBuilder result = new StringBuilder();
        Bson filter = Filters.and(
                Filters.gt("age_id", 25),
                Filters.eq("occupation", "Administrator")
        );

        // Выполняем запрос (например, получаем все документы)
        FindIterable<Document> documents = collection.find( Filters.regex("gender", selectedItem));
//        FindIterable<Document> documents = collection.find(Filters.and(Filters.gt("age_id", 18), Filters.regex("occupation", "^Admin"))) // Фильтр
//                .sort(Sorts.descending("age_id")) // Сортировка
//                .projection(Projections.fields(Projections.include("occupation", "age_id"), Projections.excludeId())) // Проекция
//                .limit(10) // Лимит
//                .skip(2); // Пропуск


        // Обрабатываем результаты
        for (Document doc : documents) {
            result.append(doc.toJson()).append("\n"); // Добавляем каждый документ в виде JSON
        }

        return result.toString();
    }
}