package ec.edu.espe.condomanagementu2.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AmountDAOTest {

    @Test
    public void testCreateAmountWithExtremeValues() {
        AmountDAO dao = new AmountDAO();

        // Probar con valores vacíos
        System.out.println("Test con valores vacíos:");
        dao.createAmount("", "", 0, 0, 0);  


        System.out.println("Test con valores negativos:");
        dao.createAmount("HouseNegative", "OwnerNegative", -100, -1, -1); 


        System.out.println("Test con números grandes:");
        dao.createAmount("HouseMax", "OwnerMax", Integer.MAX_VALUE, 1000000, 1000000); 


        System.out.println("Test con cadenas largas:");
        dao.createAmount("A".repeat(1000), "B".repeat(1000), 10, 1, 1);  

        // Si todo va bien, pasamos el test
        assertTrue(true);
    }

    @Test
    public void testUpdateAmountWithExtremeValues() {
        AmountDAO dao = new AmountDAO();

        // Probar con valores vacíos
        System.out.println("Test con valores vacíos:");
        dao.updateAmount("", "", 0, 0, 0);

        // Probar con valores negativos
        System.out.println("Test con valores negativos:");
        dao.updateAmount("HouseNegative", "OwnerNegative", -200, -2, -2);

        // Probar con números grandes y cadenas largas
        System.out.println("Test con números grandes y cadenas largas:");
        dao.updateAmount("HouseMax".repeat(100), "OwnerMax".repeat(100), Integer.MAX_VALUE, 1000000, 1000000);

        // Si todo va bien, pasamos el test
        assertTrue(true);
    }

    @Test
    public void testDeleteAmountWithExtremeValues() {
        AmountDAO dao = new AmountDAO();

        // Probar con valores inexistentes
        System.out.println("Test con valores inexistentes:");
        dao.deleteAmount("NonExistentHouse", "UnknownOwner", 0, 0, 0);

        // Probar con cadenas largas y valores extremos
        System.out.println("Test con cadenas largas y valores extremos:");
        dao.deleteAmount("HouseBig".repeat(100), "OwnerBig".repeat(100), Integer.MAX_VALUE, 1000000, 1000000);

        // Si todo va bien, pasamos el test
        assertTrue(true);
    }
}
