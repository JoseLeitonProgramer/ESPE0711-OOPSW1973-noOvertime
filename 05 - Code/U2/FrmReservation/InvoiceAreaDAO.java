package ec.edu.espe.condomanagementu2.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ec.edu.espe.condomanagementu2.model.InvoiceArea;
import utils.ConnectionMongoDB; // Importar la clase ConnectionMongoDB
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel Manosalvas
 */
public class InvoiceAreaDAO {

    private MongoCollection<Document> collection;

    public InvoiceAreaDAO() {
        // Establecer conexión a MongoDB usando ConnectionMongoDB
        MongoDatabase database = ConnectionMongoDB.getDatabase(); // Obtener la base de datos
        this.collection = database.getCollection("factura_area");
    }

    // Insertar una nueva factura en la base de datos
    public void insert(InvoiceArea facturaArea) {
        Document doc = new Document("numeroFactura", facturaArea.getNumeroFactura())
                .append("fechaEmision", facturaArea.getFechaEmision())
                .append("idResidente", facturaArea.getIdResidente())
                .append("area", facturaArea.getArea())
                .append("fecha", facturaArea.getFecha())
                .append("estadoPago", facturaArea.getEstadoPago())
                .append("metodoPago", facturaArea.getMetodoPago())
                .append("montoTotal", facturaArea.getMontoTotal());

        collection.insertOne(doc);
    }

    // Obtener una factura por su número
    public InvoiceArea getByNumeroFactura(String numeroFactura) {
        Document query = new Document("numeroFactura", numeroFactura);
        Document doc = collection.find(query).first();

        if (doc != null) {
            return new InvoiceArea(
                doc.getString("numeroFactura"),
                doc.getString("fechaEmision"),
                doc.getString("idResidente"),
                doc.getString("area"),
                doc.getString("fecha"),
                doc.getString("estadoPago"),
                doc.getString("metodoPago"),
                doc.getDouble("montoTotal")
            );
        } else {
            return null;
        }
    }

    // Obtener todas las facturas
    public List<InvoiceArea> getAll() {
        List<InvoiceArea> facturaAreas = new ArrayList<>();
        for (Document doc : collection.find()) {
            InvoiceArea facturaArea = new InvoiceArea(
                doc.getString("numeroFactura"),
                doc.getString("fechaEmision"),
                doc.getString("idResidente"),
                doc.getString("area"),
                doc.getString("fecha"),
                doc.getString("estadoPago"),
                doc.getString("metodoPago"),
                doc.getDouble("montoTotal")
            );
            facturaAreas.add(facturaArea);
        }
        return facturaAreas;
    }

    // Actualizar una factura
    public void update(InvoiceArea facturaArea) {
        Document query = new Document("numeroFactura", facturaArea.getNumeroFactura());
        Document update = new Document("$set", new Document("fechaEmision", facturaArea.getFechaEmision())
                .append("idResidente", facturaArea.getIdResidente())
                .append("area", facturaArea.getArea())
                .append("fecha", facturaArea.getFecha())
                .append("estadoPago", facturaArea.getEstadoPago())
                .append("metodoPago", facturaArea.getMetodoPago())
                .append("montoTotal", facturaArea.getMontoTotal()));

        collection.updateOne(query, update);
    }

    // Eliminar una factura por su número
    public void delete(String numeroFactura) {
        Document query = new Document("numeroFactura", numeroFactura);
        collection.deleteOne(query);
    }
}
