package ec.espe.edu.condomanagement.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.JsonUtil;

public class Vehicle {
    private String vehicleId; // Identificador único del vehículo
    private String make; // Marca del vehículo
    private String model; // Modelo del vehículo
    private String licensePlate; // Placa del vehículo

    // Constructor para inicializar un vehículo con sus detalles
    public Vehicle(String vehicleId, String make, String model, String licensePlate) {
        this.vehicleId = vehicleId;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
    }

    // Métodos JSON
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("vehicleId", vehicleId);
        obj.put("make", make);
        obj.put("model", model);
        obj.put("licensePlate", licensePlate);
        return obj;
    }

    public static Vehicle fromJson(JSONObject jsonObject) {
        String vehicleId = (String) jsonObject.get("vehicleId");
        String make = (String) jsonObject.get("make");
        String model = (String) jsonObject.get("model");
        String licensePlate = (String) jsonObject.get("licensePlate");

        return new Vehicle(vehicleId, make, model, licensePlate);
    }

    public void save(String filePath) {
        JSONObject jsonObject = toJson();
        JsonUtil.saveToJson(filePath, jsonObject);
    }

    public static Vehicle load(String filePath) {
    JSONObject defaultObject = new Vehicle("defaultId", "defaultMake", "defaultModel", "defaultPlate").toJson();
    JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
    return Vehicle.fromJson(jsonObject);
}

    // Métodos de la clase original
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return "Vehicle ID: " + vehicleId + ", Make: " + make + ", Model: " + model + ", License Plate: " + licensePlate;
    }
}
