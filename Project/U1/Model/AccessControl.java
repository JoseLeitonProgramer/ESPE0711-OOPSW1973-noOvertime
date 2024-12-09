package ec.espe.edu.condomanagement.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import utils.JsonUtil;

public class AccessControl {
    private String accessControlId;
    private String residentId;
    private String vehicleId;
    private Date accessTime;

    // Constructor
    public AccessControl(String accessControlId, String residentId, String vehicleId, Date accessTime) {
        this.accessControlId = accessControlId;
        this.residentId = residentId;
        this.vehicleId = vehicleId;
        this.accessTime = accessTime;
    }

    // Métodos JSON
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("accessControlId", accessControlId);
        obj.put("residentId", residentId);
        obj.put("vehicleId", vehicleId);
        obj.put("accessTime", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(accessTime));
        return obj;
    }

    public static AccessControl fromJson(JSONObject jsonObject) {
        String accessControlId = (String) jsonObject.get("accessControlId");
        String residentId = (String) jsonObject.get("residentId");
        String vehicleId = (String) jsonObject.get("vehicleId");
        Date accessTime = null;
        try {
            accessTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse((String) jsonObject.get("accessTime"));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return new AccessControl(accessControlId, residentId, vehicleId, accessTime);
    }

    public void save(String filePath) {
        JSONObject jsonObject = toJson();
        JsonUtil.saveToJson(filePath, jsonObject);
    }

    public static AccessControl load(String filePath) {
        JSONObject defaultObject = new AccessControl("defaultId", "defaultResidentId", "defaultVehicleId", new Date()).toJson();
        JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
        return fromJson(jsonObject);
    }


    // Getters y Setters
    public String getAccessControlId() {
        return accessControlId;
    }

    public void setAccessControlId(String accessControlId) {
        this.accessControlId = accessControlId;
    }

    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    // Métodos
    public void grantAccess(String residentId, String vehicleId) {
        System.out.println("Acceso concedido a " + residentId + " con vehículo " + vehicleId);
    }

    public void logAccess(String residentId, String accessTime) {
        System.out.println("Acceso registrado para el residente " + residentId + " a las " + accessTime);
    }

    public void updateAccessControl(String accessControlId, Date newTime) {
        this.accessTime = newTime;
        System.out.println("Hora de acceso actualizada a " + newTime);
    }

    public void revokeAccess(String accessControlId) {
        System.out.println("Acceso revocado para el control ID: " + accessControlId);
    }

    @Override
    public String toString() {
        return "AccessControl{" +
                "accessControlId='" + accessControlId + '\'' +
                ", residentId='" + residentId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", accessTime=" + accessTime +
                '}';
    }
}