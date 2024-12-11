package ec.espe.edu.condomanagement.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import utils.JsonUtil;

public class AreaReservation {
    private String reservationId;
    private Resident resident;
    private String area;
    private Date reservationDate;
    private String reservationTime;

    // Constructor
    public AreaReservation(String reservationId, Resident resident, String area, Date reservationDate, String reservationTime) {
        this.reservationId = reservationId;
        this.resident = resident;
        this.area = area;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
    }

    // Métodos JSON
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("reservationId", reservationId);
        obj.put("resident", resident.toJson());
        obj.put("area", area);
        obj.put("reservationDate", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(reservationDate));
        obj.put("reservationTime", reservationTime);
        return obj;
    }

    public static AreaReservation fromJson(JSONObject jsonObject) {
        String reservationId = (String) jsonObject.get("reservationId");
        Resident resident = Resident.fromJson((JSONObject) jsonObject.get("resident"));
        String area = (String) jsonObject.get("area");
        Date reservationDate = null;
        try {
            reservationDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse((String) jsonObject.get("reservationDate"));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        String reservationTime = (String) jsonObject.get("reservationTime");

        return new AreaReservation(reservationId, resident, area, reservationDate, reservationTime);
    }

    public void save(String filePath) {
        JSONObject jsonObject = toJson();
        JsonUtil.saveToJson(filePath, jsonObject);
    }

    public static AreaReservation load(String filePath) {
    JSONObject defaultObject = new AreaReservation("defaultId", new Resident("defaultId", "defaultName", "defaultPhone"), "defaultArea", new Date(), "10:00AM").toJson();
    JSONObject jsonObject = JsonUtil.readFromJson(filePath, defaultObject);
    return AreaReservation.fromJson(jsonObject);
}


    // Métodos de la clase original
    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public void getReservationDetails() {
        System.out.println("Detalles de la reserva:");
        System.out.println("ID Reserva: " + reservationId);
        System.out.println("Residente: " + resident.getName());
        System.out.println("Área: " + area);
        System.out.println("Fecha: " + reservationDate);
        System.out.println("Hora: " + reservationTime);
    }

    public void displayReservationInfo() {
        System.out.println("Reserva en el área: " + area);
        System.out.println("Residente: " + resident.getName());
        System.out.println("Fecha: " + reservationDate);
        System.out.println("Hora: " + reservationTime);
    }

    @Override
    public String toString() {
        return "AreaReservation{" +
                "reservationId='" + reservationId + '\'' +
                ", resident=" + resident.getName() +
                ", area='" + area + '\'' +
                ", reservationDate=" + reservationDate +
                ", reservationTime='" + reservationTime + '\'' +
                '}';
    }
}
