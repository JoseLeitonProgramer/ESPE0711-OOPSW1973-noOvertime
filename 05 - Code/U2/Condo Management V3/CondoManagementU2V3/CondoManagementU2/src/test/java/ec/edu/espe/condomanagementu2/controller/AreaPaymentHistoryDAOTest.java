package ec.edu.espe.condomanagementu2.controller;

import ec.edu.espe.condomanagementu2.model.AreaPaymentHistory;
import java.util.ArrayList;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class AreaPaymentHistoryDAOTest {

    private AreaPaymentHistoryDAO dao;

    @BeforeEach
    public void setUp() {
        dao = new AreaPaymentHistoryDAO(); // Inicializa el DAO
    }

    @AfterEach
    public void tearDown() {
        dao = null; // Limpia los recursos
    }

    @Test
    public void testGetPaymentHistory_NoFilters() {
        System.out.println("getPaymentHistory - No Filters");

        // Llamar al método sin filtros
        ArrayList<AreaPaymentHistory> results = (ArrayList<AreaPaymentHistory>) dao.getPaymentHistory("Todos", "");

        assertNotNull(results, "La lista no debería ser null");
        assertFalse(results.isEmpty(), "La lista no debería estar vacía si hay datos en la base");
    }

    @Test
    public void testGetPaymentHistory_FilterByStatus() {
        System.out.println("getPaymentHistory - Filter By Status");


        List<AreaPaymentHistory> results = dao.getPaymentHistory("Pagado", "");


        assertNotNull(results, "La lista no debería ser null");
        for (AreaPaymentHistory history : results) {
            assertEquals("Pagado", history.getStatus(), "El estado del pago debería ser 'Pagado'");
        }
    }

    @Test
    public void testGetPaymentHistory_FilterByResidentId() {
        System.out.println("getPaymentHistory - Filter By ResidentId");

        // Llamar al método con un ID de residente específico
        String residentId = "residente123"; // Asegúrate de tener este ID en la base de datos
        List<AreaPaymentHistory> results = dao.getPaymentHistory("Todos", residentId);


        assertNotNull(results, "La lista no debería ser null");
        for (AreaPaymentHistory history : results) {
            assertEquals(residentId, history.getResidentId(), "El ID del residente debería coincidir");
        }
    }

    @Test
    public void testGetPaymentHistory_NoResults() {
        System.out.println("getPaymentHistory - No Results");


        List<AreaPaymentHistory> results = dao.getPaymentHistory("Inexistente", "residenteInexistente");

        // Validar que no haya resultados
        assertNotNull(results, "La lista no debería ser null");
        assertTrue(results.isEmpty(), "La lista debería estar vacía si no hay coincidencias");
    }
}
