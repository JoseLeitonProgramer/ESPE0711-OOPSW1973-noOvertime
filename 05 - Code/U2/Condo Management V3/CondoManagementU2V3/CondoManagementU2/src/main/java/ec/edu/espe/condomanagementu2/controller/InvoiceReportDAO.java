package ec.edu.espe.condomanagementu2.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.conversions.Bson;
import ec.edu.espe.condomanagementu2.model.InvoiceReport;
import com.mongodb.client.model.Filters;

import java.util.ArrayList;
import java.util.List;

public class InvoiceReportDAO {
    private MongoCollection<Document> collection;

    public InvoiceReportDAO(MongoDatabase database) {
        collection = database.getCollection("payments");  // Suponiendo que "payments" es la colección de pagos
    }

    // Obtener todas las facturas (sin filtro)
    public List<InvoiceReport> getAllInvoices() {
        List<InvoiceReport> invoices = new ArrayList<>();

        // Consulta para obtener todos los documentos de la colección
        FindIterable<Document> docs = collection.find();

        // Verificar si no hay documentos
        if (docs == null) {
            return invoices;  // Retorna la lista vacía si no hay documentos
        }

        // Iterar sobre los documentos y convertirlos en objetos InvoiceReport
        for (Document doc : docs) {
            invoices.add(new InvoiceReport(
                doc.getObjectId("_id"),
                doc.getDate("paymentDate"),
                doc.getDouble("amount"),
                doc.getString("method"),
                doc.getString("status")
            ));
        }
        return invoices;
    }

    // Obtener facturas filtradas por estado
    public List<InvoiceReport> getInvoicesByStatus(String status) {
        List<InvoiceReport> invoices = new ArrayList<>();
        Bson filter = Filters.eq("status", status);

        if (status.equals("Todos")) {
            filter = Filters.exists("status");
        }

        FindIterable<Document> docs = collection.find(filter);
        for (Document doc : docs) {
            invoices.add(new InvoiceReport(
                doc.getObjectId("_id"),
                doc.getDate("paymentDate"),
                doc.getDouble("amount"),
                doc.getString("method"),
                doc.getString("status")
            ));
        }
        return invoices;
    }
    
}