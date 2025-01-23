package ec.edu.espe.condomanagementu2.model;
import org.bson.types.ObjectId;
/**
 *
 * @author Gabriel Manosalvas 
 */


public class InvoiceArea {

    private ObjectId id;
    private String numeroFactura;
    private String fechaEmision;
    private String idResidente;
    private String area;
    private String fecha;
    private String estadoPago;
    private String metodoPago;
    private double montoTotal;

    // Constructor
    public InvoiceArea(String numeroFactura, String fechaEmision, String idResidente, String area, 
                       String fecha, String estadoPago, String metodoPago, double montoTotal) {
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.idResidente = idResidente;
        this.area = area;
        this.fecha = fecha;
        this.estadoPago = estadoPago;
        this.metodoPago = metodoPago;
        this.montoTotal = montoTotal;
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getIdResidente() {
        return idResidente;
    }

    public void setIdResidente(String idResidente) {
        this.idResidente = idResidente;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
}