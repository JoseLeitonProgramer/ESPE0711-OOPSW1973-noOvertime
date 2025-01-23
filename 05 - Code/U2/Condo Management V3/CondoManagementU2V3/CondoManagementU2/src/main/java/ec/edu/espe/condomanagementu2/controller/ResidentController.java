package ec.edu.espe.condomanagementu2.controller;
import utils.MongoDBManager;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.condomanagementu2.model.Resident;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import java.awt.List;
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

    // Crear
    public void createResident(Resident resident) {
        Document doc = new Document("id", resident.getId())
                .append("name", resident.getName())
                .append("lastName", resident.getLastName())
                .append("phone", resident.getPhone())
                .append("email", resident.getEmail());
        collection.insertOne(doc);
    }

    // Leer
    public Document readResident(String id) {
        return collection.find(eq("id", id)).first();
    }
    
    // Actualizar
     public void updateResident(Resident resident) {
        // Crear un documento actualizado con los nuevos datos del residente
        Document updatedDoc = new Document("id", resident.getId())
                .append("name", resident.getName())
                .append("lastName", resident.getLastName())
                .append("phone", resident.getPhone())
                .append("email", resident.getEmail());

        // Actualizar el documento correspondiente con los nuevos datos
        collection.updateOne(eq("id", resident.getId()), new Document("$set", updatedDoc));
    }
    // Eliminar
    public boolean deleteResident(String id) {
    Document resident = collection.find(eq("id", id)).first();

    if (resident == null) {
        return false;
    }

    // Si existe, eliminarlo
    collection.deleteOne(eq("id", id));
    return true;  
    }

    // Obtener todos los residentes (para llenar el JTable)
    public ArrayList<Resident> getAllResidents() {
    ArrayList<Resident> residents = new ArrayList<>();  // Creando la lista de residentes
    // Recorremos todos los documentos en la colección de MongoDB
    for (Document doc : collection.find()) {
        // Extraemos los valores de los campos del documento
        String id = doc.getString("id");
        String name = doc.getString("name");
        String lastName = doc.getString("lastName");
        String phone = doc.getString("phone");
        String email = doc.getString("email");

        // Creamos un nuevo objeto Resident y lo agregamos a la lista
        residents.add(new Resident(id, name, lastName, phone, email));
    }
    return residents;  // Retorna la lista de residentes
}
public boolean residentExists(String residentId) {
    try {
        Document resident = collection.find(eq("id", residentId)).first();
        return resident != null; // Retorna true si el residente existe
    } catch (Exception e) {
        System.out.println("Error al verificar residente: " + e.getMessage());
        return false;
    }
}
}
