
package ec.edu.espe.condomanagementu2.controller;

import java.awt.print.*;
import java.text.MessageFormat;
import javax.swing.JTable;
import javax.swing.JOptionPane;
/**
 *
 * @author Alexander Maza
 */
public class PrintManager {

    public static void printTable(JTable table) {
        try {
          
            PrinterJob job = PrinterJob.getPrinterJob();


            if (job.printDialog()) {

                MessageFormat header = new MessageFormat("Lista de Vehículos / Residentes");
                MessageFormat footer = new MessageFormat("Página {0}");


                boolean complete = table.print(JTable.PrintMode.FIT_WIDTH, header, footer);


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
