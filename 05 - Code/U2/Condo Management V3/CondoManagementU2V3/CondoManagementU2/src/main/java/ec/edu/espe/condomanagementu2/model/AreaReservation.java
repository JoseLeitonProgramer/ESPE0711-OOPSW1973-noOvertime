package ec.edu.espe.condomanagementu2.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Gabriel Manosalvas 
 */
public class AreaReservation {

    private String reservationId;
    private Resident resident; // Asocia el residente con la reserva
    private String area;
    private Date reservationDate;
     private String status;

    // Constructor principal
    public AreaReservation(Resident resident1, String reservationId, Date reservationDate, String string1) {
        if (reservationId == null || reservationId.isEmpty()) {
            throw new IllegalArgumentException("El ID de la reserva no puede ser nulo o vacío");
        }
        this.reservationId = reservationId;
        this.resident = resident;
        this.area = area;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    // Constructor vacío
    public AreaReservation() {
    }

    // Getters y setters
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

    // Formato de la fecha
    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(getReservationDate());
    }

    @Override
    public String toString() {
        return "AreaReservation{" +
               "reservationId='" + getReservationId() + '\'' +
               ", resident=" + (getResident() != null ? getResident().getName() : "N/A") +
               ", area='" + getArea() + '\'' +
               ", reservationDate=" + getReservationDate() +
               '}';
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}



