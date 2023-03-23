/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frm;

import DTO.ExpedienteDTO;
import controles.ctrlExpedientes;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jvale
 */
public class FrmExpedientes extends javax.swing.JFrame {
    ctrlExpedientes ctrl;
    /**
     * Creates new form FrmExpedientes
     */
    public FrmExpedientes() {
        initComponents();
        llenarTabla();
    }
    
    private void llenarTabla(){
        ctrl = new ctrlExpedientes();
        List<ExpedienteDTO> lista = new LinkedList<>();
        lista.addAll(ctrl.consultarExpedientes());
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaExpedientes.getModel();
        modeloTabla.setRowCount(0);
        lista.forEach(expediente
                -> {
            Object[] fila = new Object[1];
            fila[0] = expediente.getNombreCliente();
            modeloTabla.addRow(fila);
        });
    }
    private void buscarCliente(){
        ctrl = new ctrlExpedientes();
        ExpedienteDTO dto = ctrl.consultarExpediente(txtClienteBuscar.getText());
        if(dto!=null){
            DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaExpedientes.getModel();
            modeloTabla.setRowCount(0);
            Object[] fila = new Object[1];
            fila[0] = dto.getNombreCliente();
            modeloTabla.addRow(fila);
        }else{
            JOptionPane.showMessageDialog(this, "No hay expediente de ese cliente","Error",JOptionPane.ERROR_MESSAGE);       
            
        }
        
    }
    private String clienteSeleccionado(){
        int indice = this.tablaExpedientes.getSelectedRow();
        if (indice != -1) {
            DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaExpedientes.getModel();
            int indiceColumnaId = 0;
            String clientes = modeloTabla.getValueAt(indice, indiceColumnaId).toString();
            return clientes;
        } else {
            return null;
        }
    }
    private void mostrarDetalles(){
        ExpedienteDTO ex = ctrl.consultarExpediente(clienteSeleccionado());
        
        String pato = ex.getAntecedentesPatologicos();
        String perso = ex.getAntecedentesPersonales();
        String piel = ex.getDiagnosticoPiel();
        String uso = ex.getCosmeticosUso();
        String cliente = ex.getNombreCliente();
        
        FrmDatosExpedientes frm = new FrmDatosExpedientes();
        frm.setearDatos(pato, perso, uso, piel, cliente);
        frm.setVisible(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtClienteBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaExpedientes = new javax.swing.JTable();
        btnBuscarExpedienteCliente = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Expedientes");

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel1.setText("Cliente:");

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jScrollPane1MousePressed(evt);
            }
        });

        tablaExpedientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre cliente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaExpedientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaExpedientesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaExpedientes);

        btnBuscarExpedienteCliente.setText("Buscar");
        btnBuscarExpedienteCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarExpedienteClienteActionPerformed(evt);
            }
        });

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));

        jMenu1.setText("Menú principal");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarExpedienteCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtClienteBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarExpedienteCliente))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jScrollPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MousePressed
        
    }//GEN-LAST:event_jScrollPane1MousePressed

    private void tablaExpedientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaExpedientesMousePressed
        mostrarDetalles();
        dispose();
    }//GEN-LAST:event_tablaExpedientesMousePressed

    private void btnBuscarExpedienteClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarExpedienteClienteActionPerformed
        if(txtClienteBuscar.getText().isEmpty()){
            llenarTabla();
        }else{
            buscarCliente();
        }
    }//GEN-LAST:event_btnBuscarExpedienteClienteActionPerformed

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        dispose();
    }//GEN-LAST:event_jMenu1MousePressed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmExpedientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmExpedientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmExpedientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmExpedientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmExpedientes().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarExpedienteCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaExpedientes;
    private javax.swing.JTextField txtClienteBuscar;
    // End of variables declaration//GEN-END:variables
}
