package ec.espe.edu.condomanagement.model;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.List;
import utils.JsonUtil;

public class Resident {
    private String id;
    private String name;
    private String phone;
    private UtilityBill utilityBill;
    private List<AreaReservation> reservations;

    // Constructor
    public Resident(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.reservations = new ArrayList<>();
    }

    // Métodos JSON
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("name", name);
        obj.put("phone", phone);

        if (utilityBill != null) {
            obj.put("utilityBill", utilityBill.toJson());
        } else {
            obj.put("utilityBill", null);
        }

        JSONArray reservationsArray = new JSONArray();
        for (AreaReservation reservation : reservations) {
            reservationsArray.add(reservation.toJson());
        }
        obj.put("reservations", reservationsArray);

        return obj;
    }

    public static Resident fromJson(JSONObject jsonObject) {
        String id = (String) jsonObject.get("id");
        String name = (String) jsonObject.get("name");
        String phone = (String) jsonObject.get("phone");

        UtilityBill utilityBill = null;
        if (jsonObject.get("utilityBill") != null) {
            utilityBill = UtilityBill.fromJson((JSONObject) jsonObject.get("utilityBill"));
        }

        Resident resident = new Resident(id, name, phone);
        resident.setUtilityBill(utilityBill);

        JSONArray reservationsArray = (JSONArray) jsonObject.get("reservations");
        for (Object obj : reservationsArray) {
            resident.addReservation(AreaReservation.fromJson((JSONObject) obj));
        }

        return resident;
    }

    public void save(String filePath) {
        JSONObject jsonObject = toJson();
        JsonUtil.saveToJson(filePath, jsonObject);
    }

    public static Resident load(String filePath) {
    JSONObject defaultObject = new Resident("defaultId", "defaultName", "defaultPhone").toJson();
    JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
    return Resident.fromJson(jsonObject);
}


    // Métodos de la clase original
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUtilityBill(UtilityBill utilityBill) {
        this.utilityBill = utilityBill;
    }

    public UtilityBill getUtilityBill() {
        return utilityBill;
    }

    public void addReservation(AreaReservation reservation) {
        reservations.add(reservation);
    }

    public List<AreaReservation> getReservations() {
        return reservations;
    }

    public void displayPersonalData() {
        System.out.println("Resident ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
    }

    public void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations made.");
        } else {
            System.out.println("Reservations made by " + name + ":");
            for (AreaReservation reservation : reservations) {
                System.out.println(reservation.toString());
            }
        }
    }

    public void displayReservations() {
        System.out.println("********** " + name + "'s Reservations **********");
        if (reservations.isEmpty()) {
            System.out.println("No reservations made.");
        } else {
            for (AreaReservation reservation : reservations) {
                System.out.println(reservation.toString());
            }
        }
    }
}
