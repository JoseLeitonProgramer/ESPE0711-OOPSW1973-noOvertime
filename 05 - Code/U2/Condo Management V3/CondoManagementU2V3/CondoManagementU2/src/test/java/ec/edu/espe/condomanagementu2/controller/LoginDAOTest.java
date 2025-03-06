package ec.edu.espe.condomanagementu2.controller;

import ec.edu.espe.condomanagementu2.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginDAOTest {

    private final LoginDAO loginDAO = new LoginDAO();

    // 📌 PRUEBAS PARA VALIDAR USUARIOS

    @Test
    public void testValidateUserWithEmptyValues() {
        try {
            loginDAO.validateUser("", "", "");
            fail("Se esperaba una excepción por credenciales vacías.");
        } catch (IllegalArgumentException e) {
            assertEquals("El usuario no está registrado o el rol es incorrecto.", e.getMessage());
        }
    }

    @Test
    public void testValidateUserWithNonExistentEmail() {
        try {
            loginDAO.validateUser("nonexistent@example.com", "password", "Residente");
            fail("Se esperaba una excepción por usuario no encontrado.");
        } catch (IllegalArgumentException e) {
            assertEquals("El usuario no está registrado o el rol es incorrecto.", e.getMessage());
        }
    }

    @Test
    public void testValidateUserWithWrongPassword() throws Exception {
        User user = new User("Residente", "wrongpassword@example.com", "hashedPassword");
        try {
            loginDAO.createUser(user);
            loginDAO.validateUser("wrongpassword@example.com", "incorrectPassword", "Residente");
            fail("Se esperaba una excepción por contraseña incorrecta.");
        } catch (IllegalArgumentException e) {
            assertEquals("Contraseña incorrecta.", e.getMessage());
        }
    }

    @Test
    public void testValidateUserWithValidCredentials() {
        User user = new User("Residente", "validuser@example.com", "hashedPassword");
        try {
            loginDAO.createUser(user);
            User result = loginDAO.validateUser("validuser@example.com", "hashedPassword", "Residente");
            assertNotNull(result);
            assertEquals("validuser@example.com", result.getEmail());
        } catch (Exception e) {
            fail("No se esperaba una excepción con credenciales válidas.");
        }
    }

    @Test
    public void testValidateUserWithWrongRole() throws Exception {
        User user = new User("Residente", "userrole@example.com", "hashedPassword");
        try {
            loginDAO.createUser(user);
            loginDAO.validateUser("userrole@example.com", "hashedPassword", "Administrador");
            fail("Se esperaba una excepción por rol incorrecto.");
        } catch (IllegalArgumentException e) {
            assertEquals("El usuario no está registrado o el rol es incorrecto.", e.getMessage());
        }
    }

   
    @Test
    public void testCreateUserWithEmptyEmail() {
        User user = new User("Residente", "", "password123");
        try {
            loginDAO.createUser(user);
            fail("Se esperaba una excepción por email vacío.");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Error al insertar usuario"));
        }
    }

    @Test
    public void testCreateUserWithEmptyPassword() {
        User user = new User("Residente", "empty@example.com", "");
        try {
            loginDAO.createUser(user);
            fail("Se esperaba una excepción por contraseña vacía.");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Error al insertar usuario"));
        }
    }

    @Test
    public void testCreateUserWithValidData() {
        User user = new User("Administrador", "validuser@example.com", "hashedPassword");
        try {
            loginDAO.createUser(user);
            User retrievedUser = loginDAO.validateUser("validuser@example.com", "hashedPassword", "Administrador");
            assertNotNull(retrievedUser);
            assertEquals("validuser@example.com", retrievedUser.getEmail());
        } catch (Exception e) {
            fail("No se esperaba excepción al crear un usuario válido.");
        }
    }

    @Test
    public void testCreateUserWithDuplicateEmail() {
        User firstUser = new User("Residente", "duplicate@example.com", "password123");
        try {
            loginDAO.createUser(firstUser);
            loginDAO.createUser(firstUser);
            fail("Se esperaba una excepción por email duplicado.");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Error al insertar usuario"));
        }
    }

    @Test
    public void testCreateUserWithLongEmail() {
        String longEmail = "A".repeat(255) + "@example.com";
        User user = new User("Residente", longEmail, "password123");
        try {
            loginDAO.createUser(user);
            fail("Se esperaba una excepción por email demasiado largo.");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Error al insertar usuario"));
        }
    }

}