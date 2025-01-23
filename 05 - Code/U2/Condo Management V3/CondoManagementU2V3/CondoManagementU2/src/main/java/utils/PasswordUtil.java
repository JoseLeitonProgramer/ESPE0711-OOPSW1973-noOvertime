
package utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Klever Jami
 */
public class PasswordUtil {
     // Método para encriptar la contraseña
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Método para verificar la contraseña
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
    
}
