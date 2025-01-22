package ec.edu.espe.condomanagementu2.controller;

import ec.edu.espe.condomanagementu2.model.User;
import utils.PasswordUtil;

/**
 *
 * @author Klever Jami
 */
public class UserController {

    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    // Método para registrar un nuevo usuario
    public boolean registerUser(String email, String plainPassword, String type) {
        try {
            // Encriptar la contraseña
            String hashedPassword = PasswordUtil.hashPassword(plainPassword);

            // Crear el objeto usuario con la contraseña encriptada
            User user = new User(type, email, hashedPassword);

            // Guardar el usuario en la base de datos
            userDAO.createUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para validar login
    public boolean validateLogin(String email, String plainPassword) {
        try {
            // Buscar usuario por email
            User user = userDAO.readUserByEmail(email);

            if (user == null) {
                return false; // Usuario no encontrado
            }

            // Verificar la contraseña ingresada contra el hash almacenado
            return PasswordUtil.checkPassword(plainPassword, user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un usuario
    public boolean updateUser(String email, String newPassword, String newType) {
        try {
            // Encriptar la nueva contraseña
            String hashedPassword = PasswordUtil.hashPassword(newPassword);

            // Crear un nuevo objeto User con los datos actualizados
            User updatedUser = new User(newType, email, hashedPassword);

            // Actualizar el usuario en la base de datos
            userDAO.updateUserByEmail(email, updatedUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un usuario
    public boolean deleteUser(String email) {
        try {
            userDAO.deleteUserByEmail(email);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
