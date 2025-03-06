package ec.edu.espe.condomanagementu2.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.condomanagementu2.model.Vehicle;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import utils.MongoDBManager;

public class VehicleController {
    private final MongoCollection<Document> collection;

    public VehicleController() {
        MongoDatabase database = MongoDBManager.getDatabase();
        collection = database.getCollection("vehicles");
    }

    
    public void createVehicle(Vehicle vehicle) {
        Document doc = new Document("VehicleID", vehicle.getVehicleID())
                .append("make", vehicle.getMake())
                .append("model", vehicle.getModel())
                .append("licensePlate", vehicle.getLicensePlate());
        collection.insertOne(doc);
    }

  
    public Vehicle readVehicle(String VehicleID) {
        Document doc = collection.find(eq("VehicleID", VehicleID)).first();
        if (doc != null) {
            return new Vehicle(
                    doc.getString("VehicleID"),
                    doc.getString("make"),
                    doc.getString("model"),
                    doc.getString("licensePlate")
            );
        }
        return null;
    }

  
    public void updateVehicle(String VehicleID, Vehicle updatedVehicle) {
        Document updatedDoc = new Document("VehicleID", updatedVehicle.getVehicleID())
                .append("make", updatedVehicle.getMake())
                .append("model", updatedVehicle.getModel())
                .append("licensePlate", updatedVehicle.getLicensePlate());
        collection.updateOne(eq("VehicleID", VehicleID), new Document("$set", updatedDoc));
    }


   public boolean deleteVehicle(String vehicleID) {
    Document vehicle = collection.find(eq("VehicleID", vehicleID)).first();


    if (vehicle == null) {
        return false; 
    }


    collection.deleteOne(eq("VehicleID", vehicleID));
    return true;  
}

 
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        for (Document doc : collection.find()) {
            vehicles.add(new Vehicle(
                    doc.getString("VehicleID"),
                    doc.getString("make"),
                    doc.getString("model"),
                    doc.getString("licensePlate")
            ));
        }
        return vehicles;
    }
}