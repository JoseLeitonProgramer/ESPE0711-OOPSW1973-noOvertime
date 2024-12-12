package ec.espe.edu.condomanagement.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.JsonUtil;

public class ParkingLot {
    private int spaceNumber;
    private boolean isOccupied;
    private Vehicle vehicle;
    private Resident tenant;

    // Constructor
    public ParkingLot(int spaceNumber) {
        this.spaceNumber = spaceNumber;
        this.isOccupied = false; // Inicialmente el espacio está desocupado
        this.vehicle = null;
        this.tenant = null;
    }

    // Métodos JSON
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("spaceNumber", spaceNumber);
        obj.put("isOccupied", isOccupied);

        if (vehicle != null) {
            obj.put("vehicle", vehicle.toJson());
        } else {
            obj.put("vehicle", null);
        }

        if (tenant != null) {
            obj.put("tenant", tenant.toJson());
        } else {
            obj.put("tenant", null);
        }

        return obj;
    }

    public static ParkingLot fromJson(JSONObject jsonObject) {
        int spaceNumber = ((Long) jsonObject.get("spaceNumber")).intValue();
        boolean isOccupied = (Boolean) jsonObject.get("isOccupied");

        Vehicle vehicle = null;
        if (jsonObject.get("vehicle") != null) {
            vehicle = Vehicle.fromJson((JSONObject) jsonObject.get("vehicle"));
        }

        Resident tenant = null;
        if (jsonObject.get("tenant") != null) {
            tenant = Resident.fromJson((JSONObject) jsonObject.get("tenant"));
        }

        ParkingLot parkingLot = new ParkingLot(spaceNumber);
        parkingLot.setOccupied(isOccupied);
        parkingLot.setVehicle(vehicle);
        parkingLot.setTenant(tenant);

        return parkingLot;
    }

    public void save(String filePath) {
        JSONObject jsonObject = toJson();
        JsonUtil.saveToJson(filePath, jsonObject);
    }

    public static ParkingLot load(String filePath) {
    JSONObject defaultObject = new ParkingLot(0).toJson();
    JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
    return ParkingLot.fromJson(jsonObject);
}

    // Métodos de la clase original
    public int getSpaceNumber() {
        return spaceNumber;
    }

    public void setSpaceNumber(int spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Resident getTenant() {
        return tenant;
    }

    public void setTenant(Resident tenant) {
        this.tenant = tenant;
    }

    public void assignVehicle(Vehicle vehicle, Resident tenant) {
        if (!isOccupied) {
            this.vehicle = vehicle;
            this.tenant = tenant;
            this.isOccupied = true;
            System.out.println("Vehículo asignado correctamente al espacio " + spaceNumber);
        } else {
            System.out.println("El espacio " + spaceNumber + " ya está ocupado.");
        }
    }

    public void removeVehicle() {
        if (isOccupied) {
            System.out.println("Se ha retirado el vehículo con placa: " + vehicle.getLicensePlate());
            this.vehicle = null;
            this.tenant = null;
            this.isOccupied = false;
        } else {
            System.out.println("El espacio " + spaceNumber + " ya está vacío.");
        }
    }

    public float calculateMonthlyFee() {
        float baseFee = 50.0f; // Tarifa base por mes
        if (vehicle != null) {
            switch (vehicle.getModel().toLowerCase()) {
                case "suv":
                    return baseFee + 20.0f; // Tarifa adicional para SUVs
                default:
                    return baseFee; // Tarifa estándar para otros tipos de vehículos
            }
        }
        return 0.0f; // No hay vehículo, por lo que no hay tarifa
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "spaceNumber=" + spaceNumber +
                ", isOccupied=" + isOccupied +
                ", vehicle=" + (vehicle != null ? vehicle.getLicensePlate() : "None") +
                ", tenant=" + (tenant != null ? tenant.getName() : "None") +
                '}';
    }
}
