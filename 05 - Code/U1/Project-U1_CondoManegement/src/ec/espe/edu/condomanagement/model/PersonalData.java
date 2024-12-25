package ec.espe.edu.condomanagement.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.JsonUtil;

public class PersonalData {
    private String address;
    private String phoneNumber;
    private String email;

    // Constructor
    public PersonalData(String address, String phoneNumber, String email) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Métodos JSON
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("address", address);
        obj.put("phoneNumber", phoneNumber);
        obj.put("email", email);
        return obj;
    }

    public static PersonalData fromJson(JSONObject jsonObject) {
        String address = (String) jsonObject.get("address");
        String phoneNumber = (String) jsonObject.get("phoneNumber");
        String email = (String) jsonObject.get("email");

        return new PersonalData(address, phoneNumber, email);
    }

    public void save(String filePath) {
        JSONObject jsonObject = toJson();
        JsonUtil.saveToJson(filePath, jsonObject);
    }

    public static PersonalData load(String filePath) {
    JSONObject defaultObject = new PersonalData("defaultAddress", "defaultPhone", "defaultEmail").toJson();
    JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
    return PersonalData.fromJson(jsonObject);
}


    // Métodos de la clase original
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void getPersonalData() {
        System.out.println("Datos personales:");
        System.out.println("Dirección: " + address);
        System.out.println("Teléfono: " + phoneNumber);
        System.out.println("Correo electrónico: " + email);
    }

    public void displayPersonalData() {
        System.out.println("Dirección: " + address);
        System.out.println("Teléfono: " + phoneNumber);
        System.out.println("Correo electrónico: " + email);
    }

    @Override
    public String toString() {
        return "PersonalData{" +
                "address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
