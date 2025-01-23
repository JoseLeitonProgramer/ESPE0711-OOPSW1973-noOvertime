package ec.edu.espe.condomanagementu2.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import ec.edu.espe.condomanagementu2.model.AreaPaymentHistory;
import utils.MongoDBManager;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.MongoException;

public class AreaPaymentHistoryDAO {

    private MongoCollection<Document> paymentCollection;

    public AreaPaymentHistoryDAO() {
        // Obtener la conexión a la base de datos
        MongoDatabase database = MongoDBManager.getDatabase();
        if (database != null) {
            paymentCollection = database.getCollection("payments");
        } else {
            System.err.println("No se pudo conectar a la base de datos");
        }
    }
public List<AreaPaymentHistory> getPaymentHistory(String paymentStatus, String residentId) {
    List<AreaPaymentHistory> paymentHistories = new ArrayList<>();
    ResidentController residentController = new ResidentController();  // Crear instancia de ResidentDAO

    try {
        // Crear el filtro de la consulta
        Document query = new Document();

        if (!paymentStatus.equals("Todos")) {
            query.append("status", paymentStatus);
        }

        if (!residentId.isEmpty()) {
            query.append("residentId", residentId);
        }

        // Ejecutar la consulta en MongoDB (asumiendo que tienes una colección "payments")
        FindIterable<Document> documents = paymentCollection.find(query);

        // Convertir los resultados a objetos AreaPaymentHistory
        for (Document doc : documents) {
            // Obtener los datos del documento
            String residentIdFromDb = doc.getString("residentId");
            String area = doc.getString("area");
            Double amount = doc.getDouble("amount");
            String status = doc.getString("status");
            String id = doc.getObjectId("_id").toString();  // Obtener el ID del pago (si lo necesitas)

            // Crear un nuevo objeto AreaPaymentHistory con el estado y el ID
            AreaPaymentHistory paymentHistory = new AreaPaymentHistory(
                residentIdFromDb,   // ID del residente
                area,               // Área
                amount,             // Monto
                status,             // Estado del pago
                id                  // ID del pago
                    
            );

            // Agregar el historial de pago a la lista
            paymentHistories.add(paymentHistory);
        }
    } catch (MongoException e) {
        System.out.println("Error al obtener el historial de pagos: " + e.getMessage());
    }

    return paymentHistories;
}


}
