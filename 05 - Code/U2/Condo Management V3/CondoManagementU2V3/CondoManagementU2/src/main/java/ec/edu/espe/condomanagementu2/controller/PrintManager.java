package ec.edu.espe.condomanagementu2.controller;

import java.awt.print.*;
import java.text.MessageFormat;
import javax.swing.JTable;
import javax.swing.JOptionPane;

/**
 * Class to handle printing functionality for tables.
 * Author: Alexander Maza
 */
public class PrintManager {

    /**
     * Method to print the given JTable.
     * @param table The table to be printed.
     */
    public static void printTable(JTable table) {
        try {
            PrinterJob job = PrinterJob.getPrinterJob();

            // Show print dialog to the user
            if (job.printDialog()) {

                // Set header and footer formats for the print job
                MessageFormat header = new MessageFormat("Lista de Vehículos / Residentes");
                MessageFormat footer = new MessageFormat("Página {0}");

                // Attempt to print the table
                boolean complete = table.print(JTable.PrintMode.FIT_WIDTH, header, footer);

                // Show appropriate message based on the print result
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                if (complete) {
                    JOptionPane.showMessageDialog(null, "Impresión completada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "La impresión fue cancelada.");
                }
            }
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(null, "Error al imprimir: " + e.getMessage());
        }
    }
}
