package ec.espe.edu.condomanagement.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import utils.JsonUtil;

public class Payment {
    private String paymentId;
    private UtilityBill bill;
    private float paymentAmount;
    private Date paymentDate;
    private String paymentMethod;

    // Constructor
    public Payment(String paymentId, UtilityBill bill, float paymentAmount, Date paymentDate, String paymentMethod) {
        this.paymentId = paymentId;
        this.bill = bill;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    // Métodos JSON
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("paymentId", paymentId);
        obj.put("bill", bill.toJson());
        obj.put("paymentAmount", paymentAmount);
        obj.put("paymentDate", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(paymentDate));
        obj.put("paymentMethod", paymentMethod);
        return obj;
    }

    public static Payment fromJson(JSONObject jsonObject) {
        String paymentId = (String) jsonObject.get("paymentId");
        UtilityBill bill = UtilityBill.fromJson((JSONObject) jsonObject.get("bill"));
        float paymentAmount = ((Double) jsonObject.get("paymentAmount")).floatValue();
        Date paymentDate = null;
        try {
            paymentDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse((String) jsonObject.get("paymentDate"));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        String paymentMethod = (String) jsonObject.get("paymentMethod");

        return new Payment(paymentId, bill, paymentAmount, paymentDate, paymentMethod);
    }

    public void save(String filePath) {
        JSONObject jsonObject = toJson();
        JsonUtil.saveToJson(filePath, jsonObject);
    }

    public static Payment load(String filePath) {
    JSONObject defaultObject = new Payment("defaultId", new UtilityBill("defaultId", new Resident("defaultId", "defaultName", "defaultPhone"), 0), 0, new Date(), "defaultMethod").toJson();
    JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
    return Payment.fromJson(jsonObject);
}

    // Métodos de la clase original
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public UtilityBill getBill() {
        return bill;
    }

    public void setBill(UtilityBill bill) {
        this.bill = bill;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void getPaymentDetails() {
        System.out.println("Detalles del pago:");
        System.out.println("ID de pago: " + paymentId);
        System.out.println("Factura ID: " + bill.getBillId());
        System.out.println("Monto del pago: " + paymentAmount);
        System.out.println("Fecha del pago: " + paymentDate);
        System.out.println("Método de pago: " + paymentMethod);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", bill=" + bill.getBillId() +
                ", paymentAmount=" + paymentAmount +
                ", paymentDate=" + paymentDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
