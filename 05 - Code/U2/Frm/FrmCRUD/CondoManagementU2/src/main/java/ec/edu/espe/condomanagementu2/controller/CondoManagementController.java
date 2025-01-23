
package ec.edu.espe.condomanagementu2.controller;

import javax.swing.JTextField;

/**
 *
 * @author gustavo
 */
public class CondoManagementController {
    // Método para validar ID (solo números)
public boolean validateID(JTextField txtID) {
    String texto = txtID.getText();
    return texto.matches("\\d*"); // Acepta solo números
}

// Método para validar nombre (solo letras)
public boolean validateName(JTextField txtName) {
    String texto = txtName.getText();
    return texto.matches("[a-zA-Z]+"); // Acepta solo letras
}

// Método para validar apellidos (solo letras)
public boolean validateLastName(JTextField txtLastName) {
    String texto = txtLastName.getText();
    return texto.matches("[a-zA-Z]+"); // Acepta solo letras
}

// Método para validar teléfono (solo números)
public boolean validatePhone(JTextField txtPhone) {
    String texto = txtPhone.getText();
    return texto.matches("\\d*"); // Acepta solo números
}

}
