package ec.edu.espe.condomanagementu2.controller;

import com.mongodb.client.MongoDatabase;
import ec.edu.espe.condomanagementu2.model.InvoiceReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import utils.MongoDBManager;

public class InvoiceReportDAOTest {

    private InvoiceReportDAO invoiceReportDAO;

    @BeforeEach
    public void setUp() {
        MongoDBManager database = getTestDatabase();
        invoiceReportDAO = new InvoiceReportDAO((MongoDatabase) database);
    }

    private MongoDBManager getTestDatabase() {
        return new MongoDBManager(); 
    }

    @Test
    public void testGetAllInvoicesEmpty() {
        List<InvoiceReport> invoices = invoiceReportDAO.getAllInvoices();
        assertTrue(invoices.isEmpty(), "Se esperaba que la lista de facturas esté vacía.");
    }

    @Test
    public void testGetAllInvoicesWithNullDocument() {
        List<InvoiceReport> invoices = invoiceReportDAO.getAllInvoices();
        assertTrue(invoices.isEmpty(), "Se esperaba que la lista de facturas esté vacía cuando no hay documentos.");
    }

    @Test
    public void testGetInvoicesByStatusNotFound() {
        List<InvoiceReport> invoices = invoiceReportDAO.getInvoicesByStatus("Inexistente");
        assertTrue(invoices.isEmpty(), "Se esperaba que la lista de facturas esté vacía al buscar un estado no existente.");
    }

    @Test
    public void testGetInvoicesByStatusEmptyStatus() {
        List<InvoiceReport> invoices = invoiceReportDAO.getInvoicesByStatus("");
        assertTrue(invoices.isEmpty(), "Se esperaba que la lista de facturas esté vacía al pasar un estado vacío.");
    }

    @Test
    public void testGetInvoicesByStatusNullStatus() {
        List<InvoiceReport> invoices = invoiceReportDAO.getInvoicesByStatus(null);
        assertTrue(invoices.isEmpty(), "Se esperaba que la lista de facturas esté vacía al pasar un estado nulo.");
    }

    @Test
    public void testGetInvoicesByStatusWithEmptyList() {
        List<InvoiceReport> invoices = invoiceReportDAO.getInvoicesByStatus("Pagado");
        assertTrue(invoices.isEmpty(), "Se esperaba que la lista de facturas esté vacía si no existen facturas con el estado 'Pagado'.");
    }

    @Test
    public void testGetInvoicesByStatusWithInvalidStatus() {
        List<InvoiceReport> invoices = invoiceReportDAO.getInvoicesByStatus("InvalidStatus");
        assertTrue(invoices.isEmpty(), "Se esperaba que la lista de facturas esté vacía si el estado no es válido.");
    }


}
