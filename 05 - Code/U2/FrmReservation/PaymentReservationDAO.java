package ec.edu.espe.condomanagementu2.controller;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import ec.edu.espe.condomanagementu2.model.PaymentReservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
/**
 *
 * @author Gabriel Manosalvas 
 */
public class PaymentReservationDAO {
    private MongoCollection<Document> collection;

    public PaymentReservationDAO(MongoDatabase database) {
        collection = database.getCollection("payments");
    }

    public List<PaymentReservation> getAllPayments() {
        List<PaymentReservation> payments = new ArrayList<>();
        FindIterable<Document> docs = collection.find();  // Obtener todos los documentos de la colección "payments"
        
        for (Document doc : docs) {
            PaymentReservation payment = new PaymentReservation(
                doc.getObjectId("_id"),
                doc.getDate("paymentDate"),
                doc.getDouble("amount"),
                doc.getString("method"),
                doc.getString("status")
            );
            payments.add(payment);
        }
        return payments;
    }


public List<PaymentReservation> getPaymentsById(String paymentId) {
    List<PaymentReservation> payments = new ArrayList<>();
    try {
        Document doc = collection.find(eq("_id", new ObjectId(paymentId))).first();
        if (doc != null) {
            payments.add(new PaymentReservation(
                doc.getObjectId("_id"),
                doc.getDate("paymentDate"),
                doc.getDouble("amount"),
                doc.getString("method"),
                doc.getString("status")
            ));
        }
    } catch (Exception e) {
        System.out.println("Error al obtener pagos por ID: " + e.getMessage());
    }
    return payments;

    
}
public boolean paymentExists(String paymentId) {
    try {
        Document payment = collection.find(eq("_id", new ObjectId(paymentId))).first();
        return payment != null; // Retorna true si el pago existe
    } catch (Exception e) {
        System.out.println("Error al verificar el ID del pago: " + e.getMessage());
        return false;
    }
}
}