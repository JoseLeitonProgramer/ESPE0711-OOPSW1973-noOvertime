package ec.edu.espe.condomanagementu2.controller;

import ec.edu.espe.condomanagementu2.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {

    private final UserDAO userDAO = new UserDAO();

    @Test
    public void testCreateUserWithValidData() {
        User newUser = new User("Administrador", "validuser@example.com", "password123");

        userDAO.createUser(newUser);
        User retrievedUser = userDAO.readUserByEmail("validuser@example.com");

        assertNotNull(retrievedUser);
        assertEquals("validuser@example.com", retrievedUser.getEmail());
        assertEquals("Administrador", retrievedUser.getType());
    }

    @Test
    public void testCreateUserWithDuplicateEmail() {
        User firstUser = new User("Residente", "duplicateuser@example.com", "password123");
        userDAO.createUser(firstUser);

        User duplicateUser = new User("Administrador", "duplicateuser@example.com", "newpassword123");

        try {
            userDAO.createUser(duplicateUser);
            fail("Se esperaba una excepción por email duplicado.");
        } catch (IllegalArgumentException e) {
            assertEquals("El email ya está registrado.", e.getMessage());
        }
    }

    @Test
    public void testCreateUserWithEmptyEmail() {
        User user = new User("Residente", "", "password123");

        try {
            userDAO.createUser(user);
            fail("Se esperaba una excepción por email vacío.");
        } catch (IllegalArgumentException e) {
            assertEquals("El email no puede estar vacío.", e.getMessage());
        }
    }

    @Test
    public void testCreateUserWithEmptyPassword() {
        User user = new User("Residente", "empty@example.com", "");

        try {
            userDAO.createUser(user);
            fail("Se esperaba una excepción por contraseña vacía.");
        } catch (IllegalArgumentException e) {
            assertEquals("La contraseña no puede estar vacía.", e.getMessage());
        }
    }

    @Test
    public void testCreateUserWithLongEmail() {
        String longEmail = new String(new char[250]).replace("\0", "a") + "@example.com";
        User user = new User("Residente", longEmail, "password123");

        try {
            userDAO.createUser(user);
            fail("Se esperaba una excepción por email demasiado largo.");
        } catch (IllegalArgumentException e) {
            assertEquals("El email excede la longitud permitida.", e.getMessage());
        }
    }

    @Test
    public void testReadUserByEmail() {
        User newUser = new User("Residente", "readuser@example.com", "password123");
        userDAO.createUser(newUser);

        User retrievedUser = userDAO.readUserByEmail("readuser@example.com");

        assertNotNull(retrievedUser);
        assertEquals("readuser@example.com", retrievedUser.getEmail());
        assertEquals("Residente", retrievedUser.getType());
    }

    @Test
    public void testReadUserByEmailNotFound() {
        User retrievedUser = userDAO.readUserByEmail("unknown@example.com");
        assertNull(retrievedUser);
    }

    @Test
    public void testUpdateUserByEmail() {
        User user = new User("Residente", "updateuser@example.com", "password123");
        userDAO.createUser(user);

        User updatedUser = new User("Administrador", "updateuser@example.com", "newpassword123");
        userDAO.updateUserByEmail("updateuser@example.com", updatedUser);

        User retrievedUser = userDAO.readUserByEmail("updateuser@example.com");
        assertNotNull(retrievedUser);
        assertEquals("Administrador", retrievedUser.getType());
    }

    @Test
    public void testUpdateUserByEmailNotFound() {
        User updatedUser = new User("Administrador", "nonexistent@example.com", "newpassword123");

        try {
            userDAO.updateUserByEmail("nonexistent@example.com", updatedUser);
            fail("Se esperaba una excepción por usuario no encontrado.");
        } catch (IllegalArgumentException e) {
            assertEquals("El usuario no existe.", e.getMessage());
        }
    }

    @Test
    public void testDeleteUserByEmail() {
        User user = new User("Residente", "deleteuser@example.com", "password123");
        userDAO.createUser(user);

        userDAO.deleteUserByEmail("deleteuser@example.com");

        User retrievedUser = userDAO.readUserByEmail("deleteuser@example.com");
        assertNull(retrievedUser);
    }

    @Test
    public void testDeleteUserByEmailNotFound() {
        try {
            userDAO.deleteUserByEmail("nonexistent@example.com");
            fail("Se esperaba una excepción por usuario no encontrado.");
        } catch (IllegalArgumentException e) {
            assertEquals("El usuario no existe.", e.getMessage());
        }
    }

    @Test
    public void testValidateLoginWithValidCredentials() {
        User user = new User("Residente", "loginuser@example.com", "password123");
        userDAO.createUser(user);

        boolean loginResult = userDAO.validateLogin("loginuser@example.com", "password123");

        assertTrue(loginResult);
    }

    @Test
    public void testValidateLoginWithInvalidPassword() {
        User user = new User("Residente", "wrongpassword@example.com", "password123");
        userDAO.createUser(user);

        boolean loginResult = userDAO.validateLogin("wrongpassword@example.com", "incorrect");

        assertFalse(loginResult);
    }

    @Test
    public void testValidateLoginWithNonExistentUser() {
        boolean loginResult = userDAO.validateLogin("nonexistent@example.com", "password123");
        assertFalse(loginResult);
    }

    @Test
    public void testValidateLoginWithEmptyEmail() {
        try {
            userDAO.validateLogin("", "password123");
            fail("Se esperaba una excepción por email vacío.");
        } catch (IllegalArgumentException e) {
            assertEquals("El email no puede estar vacío.", e.getMessage());
        }
    }

    @Test
    public void testValidateLoginWithEmptyPassword() {
        try {
            userDAO.validateLogin("empty@example.com", "");
            fail("Se esperaba una excepción por contraseña vacía.");
        } catch (IllegalArgumentException e) {
            assertEquals("La contraseña no puede estar vacía.", e.getMessage());
        }
    }
}
