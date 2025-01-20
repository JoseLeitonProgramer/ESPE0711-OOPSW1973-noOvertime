package ec.edu.espe.condomanagementu2.controller;

import utils.ConnectionMongoDB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import ec.edu.espe.condomanagementu2.model.AreaReservation;
import ec.edu.espe.condomanagementu2.model.Resident;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.mongodb.MongoException;

public class AreaReservationDAO {
    private MongoCollection<Document> collection;

    public AreaReservationDAO() {
        MongoDatabase database = ConnectionMongoDB.getDatabase();
        collection = database.getCollection("area_reservations");
    }

    // Crear
    public void createReservation(AreaReservation reservation) {
        try {
            if (readReservation(reservation.getReservationId()) != null) {
                System.out.println("Reserva con este ID ya existe.");
                return;
            }

            Document doc = new Document("reservationId", reservation.getReservationId())
                    .append("resident", new Document("id", reservation.getResident().getId())
                            .append("name", reservation.getResident().getName())
                            .append("lastName", reservation.getResident().getLastName())
                            .append("phone", reservation.getResident().getPhone()))
                    .append("area", reservation.getArea())
                    .append("reservationDate", reservation.getReservationDate());
            collection.insertOne(doc);
            System.out.println("Reserva creada con éxito.");
        } catch (MongoException e) {
            System.out.println("Error al crear la reserva: " + e.getMessage());
        }
    }

    // Leer
    public Document readReservation(String reservationId) {
        try {
            return collection.find(eq("reservationId", reservationId)).first();
        } catch (MongoException e) {
            System.out.println("Error al leer la reserva: " + e.getMessage());
            return null;
        }
    }

    // Actualizar
    public void updateReservation(String reservationId, AreaReservation reservation) {
        try {
            Document updatedDoc = new Document("resident", new Document("id", reservation.getResident().getId())
                            .append("name", reservation.getResident().getName())
                            .append("lastName", reservation.getResident().getLastName())
                            .append("phone", reservation.getResident().getPhone()))
                    .append("area", reservation.getArea())
                    .append("reservationDate", reservation.getReservationDate());

            collection.updateOne(eq("reservationId", reservationId), new Document("$set", updatedDoc));
            System.out.println("Reserva actualizada con éxito.");
        } catch (MongoException e) {
            System.out.println("Error al actualizar la reserva: " + e.getMessage());
        }
    }

    // Eliminar
    public void deleteReservation(String reservationId) {
        try {
            collection.deleteOne(eq("reservationId", reservationId));
            System.out.println("Reserva eliminada con éxito.");
        } catch (MongoException e) {
            System.out.println("Error al eliminar la reserva: " + e.getMessage());
        }
    }

    // Obtener una única reserva por ResidentId
    public AreaReservation getReservationByResidentId(String residentId) {
        Document query = new Document("resident.id", residentId);
        Document reservationDoc = collection.find(query).first();

        if (reservationDoc != null) {
            return convertDocumentToAreaReservation(reservationDoc);
        }
        return null;
    }

    // Obtener todas las reservas por ResidentId
    public List<AreaReservation> getReservationsByResidentId(String residentId) {
        List<AreaReservation> reservations = new ArrayList<>();
        try {
            Document query = new Document("resident.id", residentId);
            FindIterable<Document> reservationDocs = collection.find(query);

            for (Document doc : reservationDocs) {
                AreaReservation reservation = convertDocumentToAreaReservation(doc);
                if (reservation != null) {
                    reservations.add(reservation);
                }
            }
        } catch (MongoException e) {
            System.out.println("Error al obtener las reservas: " + e.getMessage());
        }
        return reservations;
    }

    // Método auxiliar para convertir Document a AreaReservation
    private AreaReservation convertDocumentToAreaReservation(Document doc) {
        try {
            Document residentDoc = doc.get("resident", Document.class);
            
            Resident resident = new Resident(
                residentDoc.getString("id"),
                residentDoc.getString("name"),
                residentDoc.getString("lastName"),
                residentDoc.getString("phone")
            );
            
            AreaReservation reservation = new AreaReservation(
                doc.getString("reservationId"),
                doc.getString("area"),
                doc.getDate("reservationDate")
            );
            
            reservation.setResident(resident);
            return reservation;
        } catch (Exception e) {
            System.out.println("Error al convertir documento a reserva: " + e.getMessage());
            return null;
        }
    }
}