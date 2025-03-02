package ec.edu.espe.condomanagementu2.controller;
import utils.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.condomanagementu2.model.Resident;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
/**
 *
 * @author Alexander Maza
 */

public class ResidentController {
    private MongoCollection<Document> collection;

    public ResidentController() {
        MongoDatabase database = MongoDBManager.getDatabase();
        collection = database.getCollection("residents");
    }


    public void createResident(Resident resident) {
        Document doc = new Document("id", resident.getId())
                .append("name", resident.getName())
                .append("lastName", resident.getLastName())
                .append("phone", resident.getPhone())
                .append("email", resident.getEmail());
        collection.insertOne(doc);
    }

 
    public Document readResident(String id) {
        return collection.find(eq("id", id)).first();
    }
    
   
     public void updateResident(Resident resident) {

        Document updatedDoc = new Document("id", resident.getId())
                .append("name", resident.getName())
                .append("lastName", resident.getLastName())
                .append("phone", resident.getPhone())
                .append("email", resident.getEmail());


        collection.updateOne(eq("id", resident.getId()), new Document("$set", updatedDoc));
    }

    public boolean deleteResident(String id) {
    Document resident = collection.find(eq("id", id)).first();

    if (resident == null) {
        return false;
    }

 
    collection.deleteOne(eq("id", id));
    return true;  
    }


    public ArrayList<Resident> getAllResidents() {
    ArrayList<Resident> residents = new ArrayList<>(); 

    for (Document doc : collection.find()) {

        String id = doc.getString("id");
        String name = doc.getString("name");
        String lastName = doc.getString("lastName");
        String phone = doc.getString("phone");
        String email = doc.getString("email");


        residents.add(new Resident(id, name, lastName, phone, email));
    }
    return residents;  
}
public boolean residentExists(String residentId) {
    try {
        Document resident = collection.find(eq("id", residentId)).first();
        return resident != null; 
    } catch (Exception e) {
        System.out.println("Error al verificar residente: " + e.getMessage());
        return false;
    }
}
}
