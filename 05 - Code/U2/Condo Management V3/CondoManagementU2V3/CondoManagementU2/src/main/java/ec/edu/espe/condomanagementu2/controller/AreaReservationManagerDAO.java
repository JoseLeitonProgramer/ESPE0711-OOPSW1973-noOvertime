package ec.edu.espe.condomanagementu2.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import ec.edu.espe.condomanagementu2.model.AreaReservation;
import ec.edu.espe.condomanagementu2.model.Resident;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.MongoDBManager;
import com.mongodb.MongoException;

public class AreaReservationManagerDAO {

    private MongoCollection<Document> reservationCollection;

    public AreaReservationManagerDAO() {
        // Obtener la conexión a la base de datos
        MongoDatabase database = MongoDBManager.getDatabase();
        if (database != null) {
            reservationCollection = database.getCollection("reservations");
        } else {
            System.err.println("No se pudo conectar a la base de datos");
        }
    }

    // 1. Crear una nueva reserva
    public void createReservation(AreaReservation areaReservation) throws MongoException {
        try {
            // Convertir AreaReservation a Document para MongoDB
            Document doc = new Document("residentId", areaReservation.getResident().getId())  // Cambié el getResidentd() por getId()
                    .append("area", areaReservation.getArea())
                    .append("reservationDate", areaReservation.getReservationDate())
                    .append("status", "Active");  // Suponiendo que la reserva está activa por defecto

            // Insertar la nueva reserva en la colección de MongoDB
            reservationCollection.insertOne(doc);
        } catch (MongoException e) {
            throw new MongoException("Error al crear la reserva: " + e.getMessage());
        }
    }

    // 2. Obtener todas las reservas
    public List<AreaReservation> getAllReservations() {
        List<AreaReservation> reservations = new ArrayList<>();
        try {
            // Obtener todos los documentos de la colección "reservations"
            FindIterable<Document> documents = reservationCollection.find();

            // Convertir cada documento en un objeto AreaReservation
            for (Document doc : documents) {
                // Crear un nuevo objeto Resident desde el documento
                Resident resident = new Resident(
                        doc.getString("residentId"),  // Asumimos que el ID está como String
                        "", "", "", ""  // Aquí puedes asignar el resto de los datos del residente si están disponibles
                );

                // Crear el objeto AreaReservation y agregarlo a la lista
                AreaReservation reservation = new AreaReservation(
                                                resident, doc.getString("area"), doc.getDate("reservationDate"), doc.getString("status")
                );
                reservations.add(reservation);
            }
        } catch (MongoException e) {
            System.out.println("Error al obtener las reservas: " + e.getMessage());
        }
        return reservations;
    }

    // 3. Cancelar una reserva (eliminarla)
    public void cancelReservation(String area, Date date) throws MongoException {
        try {
            // Crear un filtro para buscar la reserva por área y fecha
            Document query = new Document("area", area)
                    .append("reservationDate", date);

            // Eliminar el documento que coincida con los parámetros
            reservationCollection.deleteOne(query);
        } catch (MongoException e) {
            throw new MongoException("Error al cancelar la reserva: " + e.getMessage());
        }
        
    }
    // En AreaReservationManagerDAO
public AreaReservation getReservationByAreaAndDate(String area, Date date) {
    AreaReservation reservation = null;
    try {
        // Crear un filtro para buscar la reserva por área y fecha
        Document query = new Document("area", area)
                .append("reservationDate", date);

        // Buscar la reserva en la base de datos
        Document doc = reservationCollection.find(query).first();

        if (doc != null) {
            // Crear el objeto Resident desde el documento
            Resident resident = new Resident(
                    doc.getString("residentId"),  // Suponemos que el ID está como String
                    "", "", "", ""  // Aquí puedes asignar el resto de los datos del residente si están disponibles
            );

            // Crear la reserva desde el documento y asignarla
            reservation = new AreaReservation(
                                        resident, doc.getString("area"), doc.getDate("reservationDate"), doc.getString("status")
            );
        }
    } catch (MongoException e) {
        System.out.println("Error al obtener la reserva: " + e.getMessage());
    }
    return reservation;
}
// En AreaReservationManagerDAO
public void updateReservation(AreaReservation reservation) throws MongoException {
    try {
        // Crear un filtro para buscar la reserva por área y fecha
        Document query = new Document("area", reservation.getArea())
                .append("reservationDate", reservation.getReservationDate());

        // Crear el documento actualizado
        Document updatedDoc = new Document("residentId", reservation.getResident().getId())
                .append("area", reservation.getArea())
                .append("reservationDate", reservation.getReservationDate())
                .append("status", reservation.getStatus());

        // Actualizar la reserva en la base de datos
        reservationCollection.replaceOne(query, updatedDoc);
    } catch (MongoException e) {
        throw new MongoException("Error al actualizar la reserva: " + e.getMessage());
    }
}

}
