package ec.edu.espe.condomanagementu2.controller;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import org.bson.conversions.Bson;
import utils.MongoDBManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;

public class AmountDAO {

    private final MongoCollection<Document> collection;

    public AmountDAO() {
        MongoDatabase database = MongoDBManager.getDatabase(); // Obtener la base de datos usando el MongoDBManager
        collection = database.getCollection("amounts"); // Obtener la colección "amounts"
    }

    // Create
    public void createAmount(String house, String coowner, int expense, int tenant, int parkingLot) {
        try {
            Document doc = new Document("house", house)
                    .append("coowner", coowner)
                    .append("expense", expense)
                    .append("tenant", tenant)
                    .append("parkingLot", parkingLot);
            collection.insertOne(doc); // Inserta el documento en la colección
            System.out.println("Amount created successfully.");
        } catch (MongoException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error creating amount: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Update
    public void updateAmount(String house, String coowner, int expenseValue, int tenant, int parkingLot) {
        try {
            // Crear un nuevo documento con los valores actualizados
            Document updatedDoc = new Document("house", house)
                    .append("coowner", coowner)
                    .append("expense", expenseValue)
                    .append("tenant", tenant)
                    .append("parkingLot", parkingLot);
        
            // Actualizar el documento en la base de datos usando el valor de "house" como filtro
            collection.updateOne(eq("house", house), new Document("$set", updatedDoc));

            System.out.println("Amount updated successfully.");
        } catch (MongoException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating amount: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Delete
    public void deleteAmount(String house, String coowner, int expense, int tenant, int parkingLot) {
        try {
            // Crear el filtro para buscar el documento específico
            Bson filter = Filters.and(
                Filters.eq("house", house),
                Filters.eq("coowner", coowner),
                Filters.eq("expense", expense),
                Filters.eq("tenant", tenant),
                Filters.eq("parkingLot", parkingLot)
            );
        
            // Eliminar el documento de la colección
            collection.deleteOne(filter);

            System.out.println("Record deleted successfully.");
        } catch (MongoException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting record: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Load amounts into the table
    public void loadAmountsToTable(DefaultTableModel model) {
        try {
            MongoDatabase database = MongoDBManager.getDatabase(); // Obtener la base de datos usando el manager
            MongoCollection<Document> collection = database.getCollection("amounts");

            // Consultar todos los documentos de la colección
            FindIterable<Document> documents = collection.find();

            // Limpiar la tabla antes de cargar nuevos datos
            model.setRowCount(0);

            // Llenar la tabla con los datos
            for (Document doc : documents) {
                String house = doc.getString("house");
                String coowner = doc.getString("coowner");
                int expense = doc.getInteger("expense", 0);  // Usar 0 como valor por defecto si no existe
                int tenant = doc.getInteger("tenant", 0);
                int parkingLot = doc.getInteger("parkingLot", 0);

                model.addRow(new Object[]{house, coowner, expense, tenant, parkingLot}); // Agregar los datos a la tabla
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Find amounts based on search criteria
    public void findAmounts(DefaultTableModel model, String house, String coowner, String expense, String tenant, String parkingLot) {
        model.setRowCount(0); // Limpiar la tabla

        List<Bson> filters = new ArrayList<>();

        // Aplicar filtros según los valores ingresados
        try {
            if (!house.isEmpty()) {
                filters.add(eq("house", house));
            }
            if (!coowner.isEmpty()) {
                filters.add(eq("coowner", coowner));
            }
            if (!expense.isEmpty()) {
                filters.add(eq("expense", Integer.parseInt(expense)));  // Convertir expense a int
            }
            if (!tenant.isEmpty()) {
                filters.add(eq("tenant", Integer.parseInt(tenant)));  // Convertir tenant a int
            }
            if (!parkingLot.isEmpty()) {
                filters.add(eq("parkingLot", Integer.parseInt(parkingLot)));  // Convertir parkingLot a int
            }

            // Si existen filtros, aplicarlos, de lo contrario, cargar todos los documentos
            if (!filters.isEmpty()) {
                for (Document doc : collection.find(and(filters))) {
                    model.addRow(new Object[]{
                        doc.getString("house"),
                        doc.getString("coowner"),
                        doc.getInteger("expense"),
                        doc.getInteger("tenant"),
                        doc.getInteger("parkingLot")
                    });
                }
            } else {
                // Si no hay filtros, cargar todos los datos
                loadAmountsToTable(model);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers for expense, tenant, and parking lot.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Integer getAmountForHouseAndConcept(String house, String concept) {
    // Buscar el documento de la casa
    Document doc = collection.find(eq("house", house)).first();

    // Verificar que el documento existe y contiene el concepto
    if (doc != null && doc.containsKey(concept)) {
        // Obtener el valor del concepto
        Object amountObj = doc.get(concept);

        // Verificar si el valor es de tipo Integer o Double
        if (amountObj instanceof Integer) {
            return (Integer) amountObj;  // Si el monto es Integer, lo devolvemos como tal
        } else if (amountObj instanceof Double) {
            return ((Double) amountObj).intValue();  // Si es Double, lo convertimos a Integer
        } else {
            System.out.println("Tipo de dato no esperado para el monto.");
            return -1;  // Devolver -1 si el tipo de dato no es el esperado
        }
    }
    // Si no se encuentra el documento o el concepto, devolver -1
    return -1;
}


}
