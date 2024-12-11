package ec.espe.edu.condomanagement.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import utils.JsonUtil;

public class UtilityBill {
    private String billId; // Identificador de la factura
    private Resident resident;
    private float amount;
    private Date dueDate;
    private String status;

    // Constructor con validación para asignar el residente
    public UtilityBill(String billId, Resident resident, float amount) {
        if (resident == null) {
            throw new IllegalArgumentException("Resident cannot be null");
        }
        this.billId = billId;
        this.resident = resident;
        this.amount = amount;
        this.dueDate = new Date();
        this.status = "Pending";
    }

    // Constructor con todos los parámetros
    public UtilityBill(String billId, Resident resident, float amount, Date dueDate, String status) {
        if (resident == null) {
            throw new IllegalArgumentException("Resident cannot be null");
        }
        this.billId = billId;
        this.resident = resident;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Métodos JSON
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("billId", billId);
        obj.put("resident", resident.toJson());
        obj.put("amount", amount);
        obj.put("dueDate", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(dueDate));
        obj.put("status", status);
        return obj;
    }

    public static UtilityBill fromJson(JSONObject jsonObject) {
        String billId = (String) jsonObject.get("billId");
        Resident resident = Resident.fromJson((JSONObject) jsonObject.get("resident"));
        float amount = ((Double) jsonObject.get("amount")).floatValue();
        Date dueDate = null;
        try {
            dueDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse((String) jsonObject.get("dueDate"));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        String status = (String) jsonObject.get("status");

        return new UtilityBill(billId, resident, amount, dueDate, status);
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
    public String getBillId() {
        return billId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Resident getResident() {
        return resident;
    }

    public String getStatus() {
        return status;
    }

    public void generateBill() {
        System.out.println("Generando factura para: " + resident.getName());
        System.out.println("Factura ID: " + billId);
        System.out.println("Monto: " + amount);
        System.out.println("Fecha de vencimiento: " + dueDate);
        System.out.println("Estado de la factura: " + status);
    }

    public void getBillDetails() {
        System.out.println("Detalles de la factura:");
        System.out.println("Factura ID: " + billId);
        System.out.println("Residente: " + resident.getName());
        System.out.println("Monto: " + amount);
        System.out.println("Fecha de vencimiento: " + dueDate);
        System.out.println("Estado: " + status);
    }
}
