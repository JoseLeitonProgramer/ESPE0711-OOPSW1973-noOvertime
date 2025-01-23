package ec.edu.espe.condomanagementu2.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import ec.edu.espe.condomanagementu2.model.User;
import org.bson.Document;
import utils.MongoDBManager;
import utils.PasswordUtil;

public class UserDAO {

    private MongoCollection<Document> collection;

    public UserDAO() {
        MongoDatabase database = MongoDBManager.getDatabase();
        collection = database.getCollection("users"); // Una sola colección para todos los usuarios
    }

    // Crear usuario
    public void createUser(User user) {
        try {
            if (readUserByEmail(user.getEmail()) != null) {
                throw new IllegalArgumentException("El email ya está registrado.");
            }

            // Encriptar la contraseña antes de almacenarla
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());

            Document doc = new Document("type", user.getType()) // Cambiado de "role" a "type"
                    .append("email", user.getEmail())
                    .append("password", hashedPassword); // Guardar la contraseña encriptada

            collection.insertOne(doc);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el usuario: " + e.getMessage());
        }
    }

    // Leer usuario por email
    public User readUserByEmail(String email) {
        try {
            Document doc = collection.find(eq("email", email)).first();
            if (doc != null) {
                return new User(
                        doc.getString("type"), // Cambiado de "role" a "type"
                        doc.getString("email"),
                        doc.getString("password") // Recupera la contraseña encriptada
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al leer el usuario: " + e.getMessage());
        }
        return null;
    }

    // Actualizar usuario
    public void updateUserByEmail(String email, User user) {
        try {
            if (readUserByEmail(email) == null) {
                throw new IllegalArgumentException("El usuario no existe.");
            }

            // Encriptar la nueva contraseña antes de actualizarla
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());

            Document updatedDoc = new Document("type", user.getType()) // Cambiado de "role" a "type"
                    .append("email", user.getEmail())
                    .append("password", hashedPassword); // Actualizar con la contraseña encriptada

            collection.updateOne(eq("email", email), new Document("$set", updatedDoc));
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    // Eliminar usuario
    public void deleteUserByEmail(String email) {
        try {
            if (readUserByEmail(email) == null) {
                throw new IllegalArgumentException("El usuario no existe.");
            }
            collection.deleteOne(eq("email", email));
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    // Método para validar las credenciales del usuario
    public boolean validateLogin(String email, String plainPassword) {
        try {
            User user = readUserByEmail(email);
            if (user == null) {
                return false; // Usuario no encontrado
            }

            // Verificar la contraseña ingresada con la almacenada (encriptada)
            return PasswordUtil.checkPassword(plainPassword, user.getPassword());
        } catch (Exception e) {
            throw new RuntimeException("Error al validar el login: " + e.getMessage());
        }
    }
    // Metodo temporal para crear contraseña para el admin
    public static void main(String[] args) {
    String hashedPassword = PasswordUtil.hashPassword("noovertime2025"); // La contraseña será "noovertime2025"
    System.out.println("Hashed Password: " + hashedPassword);
}

    
}
