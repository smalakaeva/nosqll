package com.okmich.mysql2mongodb.migrate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public abstract class BaseMigration {

    protected String jdbcServerUrl;
    protected String jdbcUsername;
    protected String jdbcPassword;
    protected String mongoDbUrl;
    protected String mongoDbName;

    protected BaseMigration(String dbServerUrl, String dbUser, String dbPassword,
                            String mongoDbUrl, String mongoDbName) {
        this.jdbcPassword = dbPassword;
        this.jdbcServerUrl = dbServerUrl;
        this.jdbcUsername = dbUser;
        this.mongoDbName = mongoDbName;
        this.mongoDbUrl = mongoDbUrl;
    }

    protected Connection getConnection(String url, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BaseMigration.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    protected MongoDatabase getMongoDatabase(String serverUrl, String db) {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(serverUrl));
        return mongoClient.getDatabase(db);
    }

    public abstract void migrate();

    protected abstract Document rowToDocument(Object... row);

    public abstract String getDataFromMongo(String selectedItem);
}
