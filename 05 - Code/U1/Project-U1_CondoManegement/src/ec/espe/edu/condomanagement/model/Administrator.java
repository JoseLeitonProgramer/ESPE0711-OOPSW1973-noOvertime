package ec.espe.edu.condomanagement.model;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import utils.JsonUtil;

public class Administrator {
    private String adminId; // Identificador único del administrador
    private String name; // Nombre del administrador
    private ArrayList<Resident> residents; // Lista de residentes
    private ArrayList<Vehicle> vehicles; // Lista de vehículos
    private ArrayList<UtilityBill> utilityBills; // Lista de facturas de servicios
    private ArrayList<Payment> payments; // Lista de pagos realizados
    private ArrayList<AreaReservation> areaReservations; // Lista de reservas de áreas

    // Constructor para inicializar un administrador
    public Administrator(String adminId, String name) {
        this.adminId = adminId;
        this.name = name;
        this.residents = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.utilityBills = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.areaReservations = new ArrayList<>();
    }

    // Métodos JSON
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("adminId", adminId);
        obj.put("name", name);

        JSONArray residentsArray = new JSONArray();
        for (Resident resident : residents) {
            residentsArray.add(resident.toJson());
        }
        obj.put("residents", residentsArray);

        JSONArray vehiclesArray = new JSONArray();
        for (Vehicle vehicle : vehicles) {
            vehiclesArray.add(vehicle.toJson());
        }
        obj.put("vehicles", vehiclesArray);

        JSONArray utilityBillsArray = new JSONArray();
        for (UtilityBill bill : utilityBills) {
            utilityBillsArray.add(bill.toJson());
        }
        obj.put("utilityBills", utilityBillsArray);

        JSONArray paymentsArray = new JSONArray();
        for (Payment payment : payments) {
            paymentsArray.add(payment.toJson());
        }
        obj.put("payments", paymentsArray);

        JSONArray areaReservationsArray = new JSONArray();
        for (AreaReservation reservation : areaReservations) {
            areaReservationsArray.add(reservation.toJson());
        }
        obj.put("areaReservations", areaReservationsArray);

        return obj;
    }

    public static Administrator fromJson(JSONObject jsonObject) {
        String adminId = (String) jsonObject.get("adminId");
        String name = (String) jsonObject.get("name");

        Administrator admin = new Administrator(adminId, name);

        JSONArray residentsArray = (JSONArray) jsonObject.get("residents");
        for (Object obj : residentsArray) {
            admin.residents.add(Resident.fromJson((JSONObject) obj));
        }

        JSONArray vehiclesArray = (JSONArray) jsonObject.get("vehicles");
        for (Object obj : vehiclesArray) {
            admin.vehicles.add(Vehicle.fromJson((JSONObject) obj));
        }

        JSONArray utilityBillsArray = (JSONArray) jsonObject.get("utilityBills");
        for (Object obj : utilityBillsArray) {
            admin.utilityBills.add(UtilityBill.fromJson((JSONObject) obj));
        }

        JSONArray paymentsArray = (JSONArray) jsonObject.get("payments");
        for (Object obj : paymentsArray) {
            admin.payments.add(Payment.fromJson((JSONObject) obj));
        }

        JSONArray areaReservationsArray = (JSONArray) jsonObject.get("areaReservations");
        for (Object obj : areaReservationsArray) {
            admin.areaReservations.add(AreaReservation.fromJson((JSONObject) obj));
        }

        return admin;
    }

    public void save(String filePath) {
        JSONObject jsonObject = toJson();
        JsonUtil.saveToJson(filePath, jsonObject);
    }

    public static Administrator load(String filePath) {
    JSONObject defaultObject = new Administrator("A1", "Admin").toJson();
    JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
    return Administrator.fromJson(jsonObject);
}


    // Métodos de la clase original
    public ArrayList<Resident> getResidents() {
        return residents;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public ArrayList<UtilityBill> getUtilityBills() {
        return utilityBills;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public Resident findResidentById(String id) {
        for (Resident resident : residents) {
            if (resident.getId().equals(id)) {
                return resident; // Retornar el residente encontrado
            }
        }
        return null; // Si no se encuentra el residente
    }

    public void addResident(Resident resident) {
        residents.add(resident);
        System.out.println("Resident has been added.");
    }

    public void removeResident(String id) {
        Resident residentToRemove = null;
        for (Resident resident : residents) {
            if (resident.getId().equals(id)) {
                residentToRemove = resident;
                break;
            }
        }
        
        if (residentToRemove != null) {
            residents.remove(residentToRemove);
            System.out.println("Resident removed successfully.");
        } else {
            System.out.println("Resident with ID " + id + " not found.");
        }
    }

    public void addUtilityBill(UtilityBill bill) {
        utilityBills.add(bill);
        System.out.println("Factura agregada: " + bill.getBillId());
    }

    public void processPayment(Payment payment) {
        payments.add(payment);
        System.out.println("Payment processed successfully.");
    }

    public void displayAllResidents() {
        if (residents.isEmpty()) {
            System.out.println("No residents found.");
        } else {
            System.out.println("Residents:");
            for (Resident resident : residents) {
                System.out.println("ID: " + resident.getId() + ", Name: " + resident.getName());
            }
        }
    }

    public void displayAllUtilityBills() {
        if (utilityBills.isEmpty()) {
            System.out.println("No hay facturas disponibles.");
        } else {
            for (UtilityBill bill : utilityBills) {
                bill.getBillDetails();
            }
        }
    }

    public void displayAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            System.out.println("Vehicles:");
            for (Vehicle vehicle : vehicles) {
                System.out.println("License Plate: " + vehicle.getLicensePlate());
            }
        }
    }

    public void displayAllPayments() {
        if (payments.isEmpty()) {
            System.out.println("No payments found.");
        } else {
            System.out.println("Payments:");
            for (Payment payment : payments) {
                System.out.println("Payment Amount: $" + payment.getPaymentAmount());
            }
        }
    }

    public void displayAllAreaReservations() {
        if (areaReservations.isEmpty()) {
            System.out.println("No hay reservas de áreas disponibles.");
        } else {
            for (AreaReservation reservation : areaReservations) {
                reservation.getReservationDetails();
            }
        }
    }
}
