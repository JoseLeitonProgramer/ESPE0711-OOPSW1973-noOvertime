package ec.edu.espe.condomanagementu2.view;
import javax.swing.*;
import ec.edu.espe.condomanagementu2.controller.AreaReservationDAO;
import ec.edu.espe.condomanagementu2.controller.PrintManager;
import ec.edu.espe.condomanagementu2.controller.ResidentController;
import ec.edu.espe.condomanagementu2.model.AreaReservation;
import java.util.List;
import org.bson.Document;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

        
/**
 *
 * @author Gabriel Manosalvas
 */
public class FrmAreaReservationResidentID extends javax.swing.JFrame {

    /**
     * Creates new form FrmAreaReservationResidentID
     */
    public FrmAreaReservationResidentID() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Buscar Reservaciones por Residente");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        txtResidentId = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReservations = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Resident ID: ");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblReservations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Área", "Fecha", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tblReservations);

        btnRegresar.setText("Cerrar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addGap(145, 145, 145))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtResidentId, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnImprimir)
                .addGap(56, 56, 56)
                .addComponent(btnRegresar)
                .addGap(101, 101, 101))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtResidentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnImprimir))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
    String residentId = txtResidentId.getText().trim();

        if (residentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, ingresa el ID del residente", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        ResidentController residentController = new ResidentController();
        Document residentDoc = residentController.readResident(residentId);

        if (residentDoc == null) {
            JOptionPane.showMessageDialog(this, 
                "No se encontró un residente con este ID", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            AreaReservationDAO reservationDAO = new AreaReservationDAO();
            List<AreaReservation> reservations = reservationDAO.getReservationsByResidentId(residentId);

            if (reservations.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Este residente no tiene reservas", 
                    "Información", 
                    JOptionPane.INFORMATION_MESSAGE);
                clearTable();
            } else {
                updateTable(reservations);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al buscar las reservas: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
                        // Cerrar la pantalla actual
            this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        PrintManager.printTable(tblReservations);
    }//GEN-LAST:event_btnImprimirActionPerformed
private void updateTable(List<AreaReservation> reservations) {
    DefaultTableModel model = (DefaultTableModel) tblReservations.getModel();
    model.setRowCount(0); // Limpiar las filas existentes

    // Crear un objeto SimpleDateFormat para dar formato a la fecha
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    for (AreaReservation reservation : reservations) {
        model.addRow(new Object[]{
            reservation.getArea(),
            sdf.format(reservation.getReservationDate()),  // Formatear la fecha
            "Activa" // Puedes personalizar esto con el estado real de la reserva
        });
    }
}
private void clearTable() {
    DefaultTableModel model = (DefaultTableModel) tblReservations.getModel();
    model.setRowCount(0); // Limpiar la tabla
}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmAreaReservationResidentID.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAreaReservationResidentID.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAreaReservationResidentID.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAreaReservationResidentID.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAreaReservationResidentID().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblReservations;
    private javax.swing.JTextField txtResidentId;
    // End of variables declaration//GEN-END:variables
}
