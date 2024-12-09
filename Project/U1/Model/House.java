package ec.espe.edu.condomanagement.model;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import utils.JsonUtil;

public class House {
    private int numberHouse;
    private String nameOwner;
    private float expense;
    private int numberPets;
    private int numberVehicles;
    private int numberResidents;
    private ArrayList<Resident> tenants;
    private ArrayList<Vehicle> vehicles;

    // Constructor
    public House(int numberHouse, String nameOwner, float expense, int numberPets, int numberVehicles, int numberResidents) {
        this.numberHouse = numberHouse;
        this.nameOwner = nameOwner;
        this.expense = expense;
        this.numberPets = numberPets;
        this.numberVehicles = numberVehicles;
        this.numberResidents = numberResidents;
        this.tenants = new ArrayList<>();
        this.vehicles = new ArrayList<>();
    }

    // Métodos JSON
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("numberHouse", numberHouse);
        obj.put("nameOwner", nameOwner);
        obj.put("expense", expense);
        obj.put("numberPets", numberPets);
        obj.put("numberVehicles", numberVehicles);
        obj.put("numberResidents", numberResidents);

        JSONArray tenantsArray = new JSONArray();
        for (Resident tenant : tenants) {
            tenantsArray.add(tenant.toJson());
        }
        obj.put("tenants", tenantsArray);

        JSONArray vehiclesArray = new JSONArray();
        for (Vehicle vehicle : vehicles) {
            vehiclesArray.add(vehicle.toJson());
        }
        obj.put("vehicles", vehiclesArray);

        return obj;
    }

    public static House fromJson(JSONObject jsonObject) {
        int numberHouse = ((Long) jsonObject.get("numberHouse")).intValue();
        String nameOwner = (String) jsonObject.get("nameOwner");
        float expense = ((Double) jsonObject.get("expense")).floatValue();
        int numberPets = ((Long) jsonObject.get("numberPets")).intValue();
        int numberVehicles = ((Long) jsonObject.get("numberVehicles")).intValue();
        int numberResidents = ((Long) jsonObject.get("numberResidents")).intValue();

        House house = new House(numberHouse, nameOwner, expense, numberPets, numberVehicles, numberResidents);

        JSONArray tenantsArray = (JSONArray) jsonObject.get("tenants");
        for (Object obj : tenantsArray) {
            house.tenants.add(Resident.fromJson((JSONObject) obj));
        }

        JSONArray vehiclesArray = (JSONArray) jsonObject.get("vehicles");
        for (Object obj : vehiclesArray) {
            house.vehicles.add(Vehicle.fromJson((JSONObject) obj));
        }

        return house;
    }

    public void save(String filePath) {
        JSONObject jsonObject = toJson();
        JsonUtil.saveToJson(filePath, jsonObject);
    }

    public static House load(String filePath) {
    JSONObject defaultObject = new House(0, "defaultOwner", 0, 0, 0, 0).toJson();
    JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
    return House.fromJson(jsonObject);
}


    // Métodos de la clase original
    public int getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(int numberHouse) {
        this.numberHouse = numberHouse;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public float getExpense() {
        return expense;
    }

    public void setExpense(float expense) {
        this.expense = expense;
    }

    public int getNumberPets() {
        return numberPets;
    }

    public void setNumberPets(int numberPets) {
        this.numberPets = numberPets;
    }

    public int getNumberVehicles() {
        return numberVehicles;
    }

    public void setNumberVehicles(int numberVehicles) {
        this.numberVehicles = numberVehicles;
    }

    public int getNumberResidents() {
        return numberResidents;
    }

    public void setNumberResidents(int numberResidents) {
        this.numberResidents = numberResidents;
    }

    public ArrayList<Resident> getTenants() {
        return tenants;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addOwner(Resident owner) {
        this.nameOwner = owner.getName();
        this.tenants.add(owner);
        this.numberResidents++;
    }

    public void removeOwner() {
        this.nameOwner = null;
    }

    public void editOwner(String newName) {
        this.nameOwner = newName;
    }

    public void addTenant(Resident tenant) {
        this.tenants.add(tenant);
        this.numberResidents++;
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
        this.numberVehicles++;
    }

    @Override
    public String toString() {
        return "House{" +
                "numberHouse=" + numberHouse +
                ", nameOwner='" + nameOwner + '\'' +
                ", expense=" + expense +
                ", numberPets=" + numberPets +
                ", numberVehicles=" + numberVehicles +
                ", numberResidents=" + numberResidents +
                ", tenants=" + tenants +
                ", vehicles=" + vehicles +
                '}';
    }
}
