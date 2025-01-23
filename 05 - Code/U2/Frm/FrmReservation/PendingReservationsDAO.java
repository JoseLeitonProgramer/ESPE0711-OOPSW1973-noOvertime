package ec.edu.espe.condomanagementu2.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ec.edu.espe.condomanagementu2.model.AreaReservation;
import java.util.ArrayList;
import java.util.List;
import utils.ConnectionMongoDB;
import com.mongodb.MongoException;

public class PendingReservationsDAO {

    private MongoCollection<Document> reservationCollection;
    private AreaReservationManagerDAO areaReservationManagerDAO;

    public PendingReservationsDAO() {
        // Obtener la colección de reservas
        MongoDatabase database = ConnectionMongoDB.getDatabase();
        if (database != null) {
            reservationCollection = database.getCollection("reservations");
        } else {
            System.err.println("No se pudo conectar a la base de datos");
        }
        // Instanciamos el AreaReservationManagerDAO para usarlo en el filtrado
        areaReservationManagerDAO = new AreaReservationManagerDAO();
    }

    // Obtener todas las reservas pendientes
    public List<AreaReservation> getPendingReservations() {
        List<AreaReservation> pendingReservations = new ArrayList<>();
        try {
            // Obtener todas las reservas usando el AreaReservationManagerDAO
            List<AreaReservation> allReservations = areaReservationManagerDAO.getAllReservations();

            // Filtrar las reservas que tienen estado "Pending"
            for (AreaReservation reservation : allReservations) {
                if ("Pending".equals(reservation.getStatus())) {
                    pendingReservations.add(reservation);
                }
            }
        } catch (MongoException e) {
            System.out.println("Error al obtener las reservas pendientes: " + e.getMessage());
        }
        return pendingReservations;
    }
}
