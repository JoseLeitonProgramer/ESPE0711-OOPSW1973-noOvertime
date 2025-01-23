package ec.edu.espe.condomanagementu2.model;
import java.util.Date;
/**
 *
 * @author Gabriel Manosalvas 
 */


public class PendingReservation {

    private String idReserva;
    private String area;
    private Date fecha;
    private String estado;

    public PendingReservation(String idReserva, String area, Date fecha, String estado) {
        this.idReserva = idReserva;
        this.area = area;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Getters y setters
    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}