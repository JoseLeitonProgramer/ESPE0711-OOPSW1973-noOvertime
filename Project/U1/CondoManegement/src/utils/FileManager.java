package utils;

import ec.espe.edu.condomanagement.model.*;
import org.json.simple.JSONObject;

import java.util.Date;

public class FileManager {

    // Métodos para guardar y cargar AccessControl
    public static void saveAccessControl(AccessControl accessControl, String filePath) {
        accessControl.save(filePath);
    }

    public static AccessControl loadAccessControl(String filePath) {
        JSONObject defaultObject = new AccessControl("defaultId", "defaultResidentId", "defaultVehicleId", new Date()).toJson();
        JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
        return AccessControl.fromJson(jsonObject);
    }

    // Métodos para guardar y cargar Administrator
    public static void saveAdministrator(Administrator administrator, String filePath) {
        administrator.save(filePath);
    }

    public static Administrator loadAdministrator(String filePath) {
        JSONObject defaultObject = new Administrator("A1", "Admin").toJson();
        JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
        return Administrator.fromJson(jsonObject);
    }

    // Métodos para guardar y cargar AreaReservation
    public static void saveAreaReservation(AreaReservation areaReservation, String filePath) {
        areaReservation.save(filePath);
    }

    public static AreaReservation loadAreaReservation(String filePath) {
        JSONObject defaultObject = new AreaReservation("defaultId", new Resident("defaultId", "defaultName", "defaultPhone"), "defaultArea", new Date(), "10:00AM").toJson();
        JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
        return AreaReservation.fromJson(jsonObject);
    }

    // Métodos para guardar y cargar House
    public static void saveHouse(House house, String filePath) {
        house.save(filePath);
    }

    public static House loadHouse(String filePath) {
        JSONObject defaultObject = new House(0, "defaultOwner", 0, 0, 0, 0).toJson();
        JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
        return House.fromJson(jsonObject);
    }

    // Métodos para guardar y cargar ParkingLot
    public static void saveParkingLot(ParkingLot parkingLot, String filePath) {
        parkingLot.save(filePath);
    }

    public static ParkingLot loadParkingLot(String filePath) {
        JSONObject defaultObject = new ParkingLot(0).toJson();
        JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
        return ParkingLot.fromJson(jsonObject);
    }

    // Métodos para guardar y cargar Payment
    public static void savePayment(Payment payment, String filePath) {
        payment.save(filePath);
    }

    public static Payment loadPayment(String filePath) {
        JSONObject defaultObject = new Payment("defaultId", new UtilityBill("defaultId", new Resident("defaultId", "defaultName", "defaultPhone"), 0), 0, new Date(), "defaultMethod").toJson();
        JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
        return Payment.fromJson(jsonObject);
    }

    // Métodos para guardar y cargar PersonalData
    public static void savePersonalData(PersonalData personalData, String filePath) {
        personalData.save(filePath);
    }

    public static PersonalData loadPersonalData(String filePath) {
        JSONObject defaultObject = new PersonalData("defaultAddress", "defaultPhone", "defaultEmail").toJson();
        JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
        return PersonalData.fromJson(jsonObject);
    }

    // Métodos para guardar y cargar Resident
    public static void saveResident(Resident resident, String filePath) {
        resident.save(filePath);
    }

    public static Resident loadResident(String filePath) {
        JSONObject defaultObject = new Resident("defaultId", "defaultName", "defaultPhone").toJson();
        JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
        return Resident.fromJson(jsonObject);
    }

    // Métodos para guardar y cargar UtilityBill
    public static void saveUtilityBill(UtilityBill utilityBill, String filePath) {
        utilityBill.save(filePath);
    }

    public static UtilityBill loadUtilityBill(String filePath) {
        JSONObject defaultObject = new UtilityBill("defaultId", new Resident("defaultId", "defaultName", "defaultPhone"), 0).toJson();
        JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
        return UtilityBill.fromJson(jsonObject);
    }

    // Métodos para guardar y cargar Vehicle
    public static void saveVehicle(Vehicle vehicle, String filePath) {
        vehicle.save(filePath);
    }

    public static Vehicle loadVehicle(String filePath) {
        JSONObject defaultObject = new Vehicle("defaultId", "defaultMake", "defaultModel", "defaultPlate").toJson();
        JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
        return Vehicle.fromJson(jsonObject);
    }
}
