
package ec.edu.espe.condomanagementu2.controller;

import javax.swing.JTextField;

/**
 *
 * @author Alexander Maza
 */
public class ValidationController {
    
public boolean validateID(JTextField txtID) {
    String texto = txtID.getText();
    return texto.matches("\\d*"); 
}


public boolean validateName(JTextField txtName) {
    String texto = txtName.getText();
    return texto.matches("[a-zA-Z ]+"); 
}


public boolean validateLastName(JTextField txtLastName) {
    String texto = txtLastName.getText();
    return texto.matches("[a-zA-Z ]+"); 
}


public boolean validatePhone(JTextField txtPhone) {
    String texto = txtPhone.getText();
    return texto.matches("\\d*"); 
}

}
