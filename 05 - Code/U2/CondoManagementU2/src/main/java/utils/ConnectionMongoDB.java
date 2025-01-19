package utils;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Alexander Maza
 */
public class ConnectionMongoDB {
    
    private static final String CONNECTION_STRING = "mongodb+srv://jmaza:jmaza@cluster0.y11rj.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    private static final String DATABASE_NAME = "CondoAdmin";

    private static MongoClient mongoClient = null;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(CONNECTION_STRING);
        }
    return mongoClient.getDatabase(DATABASE_NAME);
    }
}
