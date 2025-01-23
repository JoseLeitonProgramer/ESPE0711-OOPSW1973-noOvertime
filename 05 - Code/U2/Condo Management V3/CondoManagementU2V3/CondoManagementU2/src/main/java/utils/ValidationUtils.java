package utils;


import javax.swing.JOptionPane;

/**
 * Clase utilitaria para validar campos comunes en los formularios.
 */
public class ValidationUtils {

    /**
     * Valida que un campo de texto no esté vacío.
     *
     * @param input
     * @param fieldName Nombre del campo para mostrar en el mensaje de error.
     * @return True si el texto es válido, False si está vacío.
     */
    public static boolean validateNotEmpty(String input, String fieldName) {
    if (input == null || input.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "El campo " + fieldName + " no puede estar vacío.");
        return false;
    }
    return true;
}




    /**
     * Valida que un texto contenga solo letras y espacios.
     *
     * @param text Texto a validar.
     * @param fieldName Nombre del campo para mostrar en el mensaje de error.
     * @return True si el texto es válido, False si contiene caracteres no permitidos.
     */
    public static boolean validateAlphabetic(String text, String fieldName) {
        if (!text.matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(null, "El campo " + fieldName + " solo debe contener letras.");
            return false;
        }
        return true;
    }

    /**
     * Valida el formato de un email.
     *
     * @param email Email a validar.
     * @return True si el email es válido, False si tiene un formato incorrecto.
     */
    public static boolean validateEmail(String email) {
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un email válido.");
            return false;
        }
        return true;
    }

    /**
     * Valida que una contraseña cumpla con los requisitos mínimos.
     *
     * @param password Contraseña a validar.
     * @return True si la contraseña es válida, False si no lo es.
     */
    public static boolean validatePassword(String password) {
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 6 caracteres.");
            return false;
        }
        return true;
    }
}
