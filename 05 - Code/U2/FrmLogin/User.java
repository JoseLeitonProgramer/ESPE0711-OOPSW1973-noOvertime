package ec.edu.espe.condomanagementu2.model;

/**
 *
 * @author Klever
 */
public class User {

    private String type;
    private String email;
    private String password;

    public User(String type, String email, String password) {
        this.type = type;
        this.email = email;
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean verifyCredential(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
}
