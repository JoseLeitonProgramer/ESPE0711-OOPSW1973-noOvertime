package ec.espe.edu.condomanagement.model;

import java.util.Calendar;

public class AreaReservation {
    private String reservationId;    // ID de la reserva
    private Resident resident;       // Residente que realiza la reserva
    private String area;             // Área reservada
    private Calendar reservationDate; // Fecha de la reserva
    private String reservationTime;  // Hora de la reserva

    // Constructor
    public AreaReservation(String reservationId, Resident resident, String area, String reservationTime, String time) {
        this.reservationId = reservationId;
        this.resident = resident;
        this.area = area;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
    }

    // Getters y Setters
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

    public Calendar getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Calendar reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    // Métodos
    public void getReservationDetails() {
        System.out.println("Detalles de la reserva:");
        System.out.println("ID Reserva: " + reservationId);
        System.out.println("Residente: " + resident.getName());
        System.out.println("Área: " + area);
        System.out.println("Fecha: " + reservationDate.getTime());  // Se muestra la fecha completa
        System.out.println("Hora: " + reservationTime);
    }

    public void displayReservationInfo() {
        System.out.println("Reserva en el área: " + area);
        System.out.println("Residente: " + resident.getName());
        System.out.println("Fecha: " + reservationDate.getTime());
        System.out.println("Hora: " + reservationTime);
    }

    @Override
    public String toString() {
        return "AreaReservation{" +
                "reservationId='" + reservationId + '\'' +
                ", resident=" + resident.getName() +
                ", area='" + area + '\'' +
                ", reservationDate=" + reservationDate.getTime() +  // Se muestra la fecha completa
                ", reservationTime='" + reservationTime + '\'' +
                '}';
    }
}
