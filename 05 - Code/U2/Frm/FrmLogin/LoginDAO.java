package ec.edu.espe.condomanagementu2.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.condomanagementu2.model.User;
import org.bson.Document;
import utils.MongoDBManager;
import utils.PasswordUtil;

public class LoginDAO {

    private final MongoCollection<Document> userCollection;

    public LoginDAO() {
        try {
            MongoDatabase database = MongoDBManager.getDatabase();
            userCollection = database.getCollection("users");
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    /**
     * Método para validar las credenciales del usuario en la base de datos.
     * 
     * @param email    Correo electrónico del usuario.
     * @param plainPassword
     * @param role     Rol del usuario (e.g., "Residente", "Administrador").
     * @return Objeto User si las credenciales son válidas.
     * @throws IllegalArgumentException Si el usuario no existe o las credenciales son incorrectas.
     */
 public User validateUser(String email, String plainPassword, String role) {
    try {
        // Buscar el documento por email y tipo de usuario
        Document query = new Document("email", email).append("type", role);
        Document userDoc = userCollection.find(query).first();

        if (userDoc == null) {
            throw new IllegalArgumentException("El usuario no está registrado o el rol es incorrecto.");
        }

        // Obtener la contraseña encriptada almacenada
        String hashedPassword = userDoc.getString("password");

        // Verificar la contraseña ingresada contra la almacenada
        if (!PasswordUtil.checkPassword(plainPassword, hashedPassword)) {
            throw new IllegalArgumentException("Contraseña incorrecta.");
        }

        // Si todo es válido, retornar el usuario
        return new User(
                userDoc.getString("type"),
                userDoc.getString("email"),
                hashedPassword // Contraseña encriptada
        );
    } catch (Exception e) {
        throw new RuntimeException("Error al validar el usuario: " + e.getMessage());
    }
}



    /**
     * Método para crear un nuevo usuario en la base de datos.
     * 
     * @param user Objeto User con los datos del usuario a crear.
     * @throws Exception Si ocurre un error al insertar el usuario.
     */
    public void createUser(User user) throws Exception {
        try {
            // Construir el documento para insertar
            Document document = new Document("email", user.getEmail())
                    .append("password", user.getPassword())
                    .append("type", user.getType());

            // Insertar el documento en la colección
            userCollection.insertOne(document);
            System.out.println("Usuario creado exitosamente: " + document.toJson());
        } catch (Exception e) {
            throw new Exception("Error al insertar usuario: " + e.getMessage());
        }
    }

}
