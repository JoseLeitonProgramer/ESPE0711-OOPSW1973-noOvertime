package ec.edu.espe.condomanagementu2.controller;

import ec.edu.espe.condomanagementu2.model.InvoiceArea;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class InvoiceAreaDAOTest {

    private final InvoiceAreaDAO invoiceAreaDAO = new InvoiceAreaDAO();

    @Test
    public void testInsertInvoiceArea() {
        InvoiceArea invoiceArea = new InvoiceArea("FAC123", "2025-03-01", "RES001", "Area1", "2025-03-01", "Pagado", "Tarjeta", 100.0);
        invoiceAreaDAO.insert(invoiceArea);

        InvoiceArea retrievedInvoice = invoiceAreaDAO.getByNumeroFactura("FAC123");
        assertNotNull(retrievedInvoice);
        assertEquals("FAC123", retrievedInvoice.getNumeroFactura());
    }

    @Test
    public void testGetByNumeroFactura() {
        InvoiceArea invoiceArea = new InvoiceArea("FAC124", "2025-03-02", "RES002", "Area2", "2025-03-02", "Pendiente", "Efectivo", 150.0);
        invoiceAreaDAO.insert(invoiceArea);

        InvoiceArea retrievedInvoice = invoiceAreaDAO.getByNumeroFactura("FAC124");
        assertNotNull(retrievedInvoice);
        assertEquals("FAC124", retrievedInvoice.getNumeroFactura());
    }

    @Test
    public void testGetByNumeroFacturaNotFound() {
        InvoiceArea retrievedInvoice = invoiceAreaDAO.getByNumeroFactura("FAC999");
        assertNull(retrievedInvoice);
    }

    @Test
    public void testGetAllInvoices() {
        InvoiceArea invoice1 = new InvoiceArea("FAC125", "2025-03-03", "RES003", "Area3", "2025-03-03", "Pagado", "Transferencia", 200.0);
        InvoiceArea invoice2 = new InvoiceArea("FAC126", "2025-03-04", "RES004", "Area4", "2025-03-04", "Pendiente", "Cheque", 250.0);
        invoiceAreaDAO.insert(invoice1);
        invoiceAreaDAO.insert(invoice2);

        List<InvoiceArea> invoices = invoiceAreaDAO.getAll();
        assertEquals(2, invoices.size());
    }

    @Test
    public void testUpdateInvoiceArea() {
        InvoiceArea invoiceArea = new InvoiceArea("FAC127", "2025-03-05", "RES005", "Area5", "2025-03-05", "Pagado", "Tarjeta", 300.0);
        invoiceAreaDAO.insert(invoiceArea);

        InvoiceArea updatedInvoice = new InvoiceArea("FAC127", "2025-03-06", "RES005", "Area6", "2025-03-06", "Pendiente", "Transferencia", 350.0);
        invoiceAreaDAO.update(updatedInvoice);

        InvoiceArea retrievedInvoice = invoiceAreaDAO.getByNumeroFactura("FAC127");
        assertNotNull(retrievedInvoice);
        assertEquals("Area6", retrievedInvoice.getArea());
        assertEquals(350.0, retrievedInvoice.getMontoTotal());
    }

    @Test
    public void testUpdateInvoiceAreaNotFound() {
        InvoiceArea updatedInvoice = new InvoiceArea("FAC999", "2025-03-07", "RES006", "Area7", "2025-03-07", "Pagado", "Cheque", 400.0);
        invoiceAreaDAO.update(updatedInvoice);

        InvoiceArea retrievedInvoice = invoiceAreaDAO.getByNumeroFactura("FAC999");
        assertNull(retrievedInvoice);
    }

    @Test
    public void testDeleteInvoiceArea() {
        InvoiceArea invoiceArea = new InvoiceArea("FAC128", "2025-03-08", "RES007", "Area8", "2025-03-08", "Pendiente", "Efectivo", 500.0);
        invoiceAreaDAO.insert(invoiceArea);

        invoiceAreaDAO.delete("FAC128");
        InvoiceArea retrievedInvoice = invoiceAreaDAO.getByNumeroFactura("FAC128");
        assertNull(retrievedInvoice);
    }

    @Test
    public void testDeleteInvoiceAreaNotFound() {
        invoiceAreaDAO.delete("FAC999");
        InvoiceArea retrievedInvoice = invoiceAreaDAO.getByNumeroFactura("FAC999");
        assertNull(retrievedInvoice);
    }

    @Test
    public void testInsertInvoiceAreaWithNullFields() {
        InvoiceArea invoiceArea = new InvoiceArea(null, "2025-03-09", "RES008", "Area9", "2025-03-09", "Pagado", "Transferencia", 600.0);
        try {
            invoiceAreaDAO.insert(invoiceArea);
            fail("Se esperaba una excepción por datos nulos.");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Error al insertar"));
        }
    }

    @Test
    public void testInsertInvoiceAreaWithEmptyFields() {
        InvoiceArea invoiceArea = new InvoiceArea("", "2025-03-10", "RES009", "Area10", "2025-03-10", "Pendiente", "Cheque", 700.0);
        try {
            invoiceAreaDAO.insert(invoiceArea);
            fail("Se esperaba una excepción por campos vacíos.");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Error al insertar"));
        }
    }

    @Test
    public void testGetAllInvoicesEmpty() {
        List<InvoiceArea> invoices = invoiceAreaDAO.getAll();
        assertTrue(invoices.isEmpty());
    }

    @Test
    public void testGetByNumeroFacturaWithNull() {
        InvoiceArea retrievedInvoice = invoiceAreaDAO.getByNumeroFactura(null);
        assertNull(retrievedInvoice);
    }

    @Test
    public void testGetByNumeroFacturaWithEmpty() {
        InvoiceArea retrievedInvoice = invoiceAreaDAO.getByNumeroFactura("");
        assertNull(retrievedInvoice);
    }

    @Test
    public void testUpdateInvoiceAreaWithNullFields() {
        InvoiceArea invoiceArea = new InvoiceArea("FAC129", "2025-03-11", "RES010", "Area11", "2025-03-11", "Pagado", "Tarjeta", 800.0);
        invoiceAreaDAO.insert(invoiceArea);

        InvoiceArea updatedInvoice = new InvoiceArea("FAC129", null, "RES010", "Area12", "2025-03-12", "Pendiente", "Transferencia", 850.0);
        invoiceAreaDAO.update(updatedInvoice);

        InvoiceArea retrievedInvoice = invoiceAreaDAO.getByNumeroFactura("FAC129");
        assertEquals("Area12", retrievedInvoice.getArea());
    }

    @Test
    public void testUpdateInvoiceAreaWithEmptyFields() {
        InvoiceArea invoiceArea = new InvoiceArea("FAC130", "2025-03-12", "RES011", "Area13", "2025-03-13", "Pendiente", "Cheque", 900.0);
        invoiceAreaDAO.insert(invoiceArea);

        InvoiceArea updatedInvoice = new InvoiceArea("FAC130", "", "RES011", "Area14", "2025-03-14", "Pagado", "Tarjeta", 950.0);
        invoiceAreaDAO.update(updatedInvoice);

        InvoiceArea retrievedInvoice = invoiceAreaDAO.getByNumeroFactura("FAC130");
        assertEquals("Area14", retrievedInvoice.getArea());
    }

    @Test
    public void testUpdateInvoiceAreaWithInvalidFactura() {
        InvoiceArea updatedInvoice = new InvoiceArea("FAC999", "2025-03-13", "RES012", "Area15", "2025-03-15", "Pagado", "Efectivo", 1000.0);
        invoiceAreaDAO.update(updatedInvoice);

        InvoiceArea retrievedInvoice = invoiceAreaDAO.getByNumeroFactura("FAC999");
        assertNull(retrievedInvoice);
    }

    @Test
    public void testDeleteInvoiceAreaWithNull() {
        try {
            invoiceAreaDAO.delete(null);
            fail("Se esperaba una excepción por número de factura nulo.");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Error al eliminar"));
        }
    }
}
