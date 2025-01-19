package ec.edu.espe.condomanagementu2.controller;
import utils.ConnectionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.condomanagementu2.model.Resident;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
/**
 *
 * @author Alexander Maza
 */

public class ResidentDAO {
    private MongoCollection<Document> collection;

    public ResidentDAO() {
        MongoDatabase database = ConnectionMongoDB.getDatabase();
        collection = database.getCollection("residents");
    }

    // Crear
    public void createResident(Resident resident) {
        Document doc = new Document("id", resident.getId())
                .append("name", resident.getName())
                .append("lastName", resident.getLastName())
                .append("phone", resident.getPhone());
        collection.insertOne(doc);
    }

    // Leer
    public Document readResident(String id) {
        return collection.find(eq("id", id)).first();
    }

    // Actualizar
    public void updateResident(String id, Resident resident) {
        Document updatedDoc = new Document("name", resident.getName())
                .append("lastName", resident.getLastName())
                .append("phone", resident.getPhone());
        collection.updateOne(eq("id", id), new Document("$set", updatedDoc));
    }

    // Eliminar
    public void deleteResident(String id) {
        collection.deleteOne(eq("id", id));
    }
}

