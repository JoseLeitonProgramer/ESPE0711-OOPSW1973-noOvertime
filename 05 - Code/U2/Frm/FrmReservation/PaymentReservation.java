package ec.edu.espe.condomanagementu2.model;
import org.bson.types.ObjectId;
import java.util.Date;
/**
 *
 * @author Gabriel Manosalvas 
 */


public class PaymentReservation {
    private ObjectId id;
    private Date paymentDate;
    private double amount;
    private String method;  // Ejemplo: "Tarjeta", "Efectivo", etc.
    private String status;  // Ejemplo: "Pagado", "Pendiente"

    public PaymentReservation(ObjectId id, Date paymentDate, double amount, String method, String status) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }

    public ObjectId getId() {
        return id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public String getStatus() {
        return status;
    }
}
