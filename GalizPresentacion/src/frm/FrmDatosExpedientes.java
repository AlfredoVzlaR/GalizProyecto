/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frm;

import DTO.ExpedienteDTO;
import controles.ctrlExpedientes;
import java.awt.Color;
import javax.swing.JOptionPane;


/**
 *
 * @author jvale
 */
public class FrmDatosExpedientes extends javax.swing.JFrame {

    ExpedienteDTO dto = new ExpedienteDTO();
    ctrlExpedientes ctrl;
    /**
     * Creates new form FrmDatosExpedientes
     */
    public FrmDatosExpedientes() {
        initComponents();
        jPanel2.setBackground(Color.decode("#ADD8E6"));
    }
    
    public void setearDatos(String pato, String perso, String cos, String piel, String cliente){
        areaPatologicos.setText(pato);
        areaPersonales.setText(perso);
        areaPiel.setText(piel);
        areaCosmeticos.setText(cos);
        txtNombreClienteDatos.setText(cliente);
        txtNombreClienteDatos.setEditable(false);
    }
    public void desSetear(){
        dto.setAntecedentesPatologicos("");
        dto.setAntecedentesPersonales("");
        dto.setCosmeticosUso("");
        dto.setDiagnosticoPiel("");
        dto.setNombreCliente("");
    }
    
    private void agregarExpediente(){
        
        ctrl = new ctrlExpedientes();
        if(areaCosmeticos.getText().isEmpty()&&areaPatologicos.getText().isEmpty()&&areaPersonales.getText().isEmpty()
                &&areaPiel.getText().isEmpty()&&txtNombreClienteDatos.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "No hay datos que añadir","Error",JOptionPane.ERROR_MESSAGE);       
            return;
        }
        
        if(!areaPatologicos.getText().isEmpty()){
            dto.setAntecedentesPatologicos(areaPatologicos.getText());
        }
        if(!areaPersonales.getText().isEmpty()){
            dto.setAntecedentesPersonales(areaPersonales.getText());
        }
        if(!areaCosmeticos.getText().isEmpty()){
            dto.setCosmeticosUso(areaCosmeticos.getText());
        }
        if(!areaPiel.getText().isEmpty()){
            dto.setDiagnosticoPiel(areaPiel.getText());
        }
        if(txtNombreClienteDatos.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "El expediente debe de asociarse a un cliente","Error",JOptionPane.ERROR_MESSAGE);       
            return;
        }else{
            dto.setNombreCliente(txtNombreClienteDatos.getText());
        }
        if(ctrl.agregarExpediente(dto)){
            JOptionPane.showMessageDialog(this, "El expediente se registró correctamente","Éxito",JOptionPane.INFORMATION_MESSAGE);       
            desSetear();
            limpiar();
            
        }
        
    }
    private void actualizarExpediente(){
        ctrl = new ctrlExpedientes();
        
        if(!areaPatologicos.getText().isEmpty()){
            dto.setAntecedentesPatologicos(areaPatologicos.getText());
        }
        if(!areaPersonales.getText().isEmpty()){
            dto.setAntecedentesPersonales(areaPersonales.getText());
        }
        if(!areaCosmeticos.getText().isEmpty()){
            dto.setCosmeticosUso(areaCosmeticos.getText());
        }
        if(!areaPiel.getText().isEmpty()){
            dto.setDiagnosticoPiel(areaPiel.getText());
        }
        if(!txtNombreClienteDatos.getText().isEmpty()){
            dto.setNombreCliente(txtNombreClienteDatos.getText());
        }
        if(ctrl.actualizarExpediente(dto)){
            JOptionPane.showMessageDialog(this, "El expediente se actualizó correctamente","Éxito",JOptionPane.INFORMATION_MESSAGE);       
            limpiar();
            txtNombreClienteDatos.setEditable(true);
        }
    }
    
    private void eliminarExpediente(){
        ctrl = new ctrlExpedientes();
        
        if(areaCosmeticos.getText().isEmpty()&&areaPatologicos.getText().isEmpty()&&areaPersonales.getText().isEmpty()
                &&areaPiel.getText().isEmpty()&&txtNombreClienteDatos.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "No hay expediente que eliminar","Error",JOptionPane.ERROR_MESSAGE);       
            return;
        }
        
        if(!areaPatologicos.getText().isEmpty()){
            dto.setAntecedentesPatologicos(areaPatologicos.getText());
        }
        if(!areaPersonales.getText().isEmpty()){
            dto.setAntecedentesPersonales(areaPersonales.getText());
        }
        if(!areaCosmeticos.getText().isEmpty()){
            dto.setCosmeticosUso(areaCosmeticos.getText());
        }
        if(!areaPiel.getText().isEmpty()){
            dto.setDiagnosticoPiel(areaPiel.getText());
        }
        if(!txtNombreClienteDatos.getText().isEmpty()){
            dto.setNombreCliente(txtNombreClienteDatos.getText());
        }
        if(ctrl.eliminarExpediente(dto)){
            JOptionPane.showMessageDialog(this, "El expediente se eliminó correctamente","Éxito",JOptionPane.INFORMATION_MESSAGE);       
            limpiar();
            
        }
    }
    
    private void limpiar(){
        txtNombreClienteDatos.setText("");
        areaCosmeticos.setText("");
        areaPatologicos.setText("");
        areaPersonales.setText("");
        areaPiel.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaPatologicos = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaPersonales = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaPiel = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txtNombreClienteDatos = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        areaCosmeticos = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos expedientes");

        jLabel2.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel2.setText("Antecedentes patológicos:");

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel3.setText("Antecedentes personales:");

        jLabel4.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel4.setText("Diagnóstico de piel:");

        jLabel5.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel5.setText("Cosmeticos en uso:");

        areaPatologicos.setColumns(20);
        areaPatologicos.setRows(5);
        jScrollPane1.setViewportView(areaPatologicos);

        areaPersonales.setColumns(20);
        areaPersonales.setRows(5);
        jScrollPane2.setViewportView(areaPersonales);

        areaPiel.setColumns(20);
        areaPiel.setRows(5);
        jScrollPane3.setViewportView(areaPiel);

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel1.setText("Nombre cliente:");

        areaCosmeticos.setColumns(20);
        areaCosmeticos.setRows(5);
        jScrollPane4.setViewportView(areaCosmeticos);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(txtNombreClienteDatos)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreClienteDatos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jMenu1.setText("Menú principal");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Expedientes");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Salir");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(txtNombreClienteDatos.isEditable()){
            agregarExpediente();
        }else{
            actualizarExpediente();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        FrmExpedientes frm = new FrmExpedientes();
        frm.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarExpediente();
    }//GEN-LAST:event_btnEliminarActionPerformed

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
//            java.util.logging.Logger.getLogger(FrmDatosExpedientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmDatosExpedientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmDatosExpedientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmDatosExpedientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmDatosExpedientes().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaCosmeticos;
    private javax.swing.JTextArea areaPatologicos;
    private javax.swing.JTextArea areaPersonales;
    private javax.swing.JTextArea areaPiel;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField txtNombreClienteDatos;
    // End of variables declaration//GEN-END:variables
}
