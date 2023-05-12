/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frm;

import DTO.ClienteDTO;
import DTO.ExpedienteDTO;
import controles.CtrlClientes;
import controles.ctrlExpedientes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;


/**
 *
 * @author jvale
 */
public class FrmDatosExpedientes extends javax.swing.JFrame {

    ExpedienteDTO dto = new ExpedienteDTO();
    ctrlExpedientes ctrl;
    DefaultComboBoxModel<ClienteDTO> clientesComboBoxModel = new DefaultComboBoxModel<>();
    List<ClienteDTO> listaClientes=null;
    CtrlClientes ctrlClientes;
    /**
     * Creates new form FrmDatosExpedientes
     */
    public FrmDatosExpedientes() {
        initComponents();
        ajustesPantalla();
        btnGuardar.setOpaque(true);
        btnEliminar.setOpaque(true);
        btnCancelar.setOpaque(true);
        btnGuardar.setBackground(Color.CYAN);
        btnEliminar.setBackground(Color.CYAN);
        btnCancelar.setBackground(Color.CYAN);
        
        menu.setOpaque(true);
        menu.setBackground(Color.decode("#C5FFF3"));
        principal.setOpaque(true);
        principal.setBackground(Color.white);
        consultas.setOpaque(true);
        consultas.setBackground(Color.white);
        ctrlClientes = new CtrlClientes();
        jPanel2.setBackground(Color.WHITE);
        listaClientes = new ArrayList<>();
        listaClientes = ctrlClientes.consultarClientes();
        llenarComboBox();
        
    }
    
     private void ajustesPantalla() {
        // Establecer las dimensiones de la ventana
        int width = 1050;
        int height = 575;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;
        setBounds(x, y, width, height);

        // Evitar que se pueda redimensionar la ventana
        setResizable(false);
    }
    
    private void llenarComboBox() {
        clientesComboBoxModel.removeAllElements();
        clientesComboBoxModel.addAll(listaClientes);
    }
    
    private String telefonoSeleccionado(){
        int indice = comboBoxClientes.getSelectedIndex();
        if (indice != -1) {
            String telefono = comboBoxClientes.getModel().getElementAt(indice).getTelefono();
            return telefono;
        } else {
            return null;
        }
    }
    private ClienteDTO cliente(String telefono){
        ClienteDTO clienteRegistrar = ctrlClientes.consultarCliente(telefono);
        return clienteRegistrar;
    }
    
    public void setearDatos(String pato, String perso, String cos, String piel, ClienteDTO cliente){
        areaPatologicos.setText(pato);
        areaPersonales.setText(perso);
        areaPiel.setText(piel);
        areaCosmeticos.setText(cos);
        clientesComboBoxModel.addElement(cliente);
    }
    public void desSetear(){
        dto.setAntecedentesPatologicos("");
        dto.setAntecedentesPersonales("");
        dto.setCosmeticosUso("");
        dto.setDiagnosticoPiel("");
        dto.setNombreCliente("");
        dto.setTelefonoCliente("");
    }
    
    private void agregarExpediente(){
        
        ctrl = new ctrlExpedientes();
        if(ctrl.consultarExpediente(telefonoSeleccionado())!=null){
            actualizarExpediente();
            return;
        }
        if(areaCosmeticos.getText().isEmpty()&&areaPatologicos.getText().isEmpty()&&areaPersonales.getText().isEmpty()
                &&areaPiel.getText().isEmpty()&&clientesComboBoxModel.getSelectedItem()==null){
            JOptionPane.showMessageDialog(this, "No hay datos que añadir","Error",JOptionPane.ERROR_MESSAGE);       
            return;
        }
        if(clientesComboBoxModel.getSelectedItem()!=null){
            ClienteDTO cDTO = cliente(telefonoSeleccionado());
            dto.setNombreCliente(cDTO.getNombre());
            dto.setTelefonoCliente(cDTO.getTelefono());
        }else{
            JOptionPane.showMessageDialog(this, "No se eligió el cliente","Error",JOptionPane.ERROR_MESSAGE);
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
        if(clientesComboBoxModel.getElementAt(0)!=null){
            dto.setNombreCliente(clientesComboBoxModel.getElementAt(0).getNombre());
            dto.setTelefonoCliente(clientesComboBoxModel.getElementAt(0).getTelefono());
        }
        if(ctrl.actualizarExpediente(dto)){
            JOptionPane.showMessageDialog(this, "El expediente se actualizó correctamente","Éxito",JOptionPane.INFORMATION_MESSAGE);       
            limpiar();
        }
    }
    
    private void eliminarExpediente(){
        desSetear();
        ctrl = new ctrlExpedientes();
        if(areaCosmeticos.getText().isEmpty()&&areaPatologicos.getText().isEmpty()&&areaPersonales.getText().isEmpty()
                &&areaPiel.getText().isEmpty()&&clientesComboBoxModel.getSize()==0){
            JOptionPane.showMessageDialog(this, "No hay expediente que eliminar","Error",JOptionPane.ERROR_MESSAGE);       
            return;
        }
        int ou = clientesComboBoxModel.getIndexOf(clientesComboBoxModel.getSelectedItem());
        ExpedienteDTO dto2=null;
        
        dto2=ctrl.consultarExpediente(clientesComboBoxModel.getElementAt(ou).getTelefono());
        
        if(dto2==null){
            JOptionPane.showMessageDialog(this, "Este cliente no tiene expediente a eliminar","Error",JOptionPane.ERROR_MESSAGE);       
            return;
        }
        
        if(clientesComboBoxModel.getElementAt(0)!=null){
            
            dto.setNombreCliente(clientesComboBoxModel.getElementAt(ou).getNombre());
            dto.setTelefonoCliente(clientesComboBoxModel.getElementAt(ou).getTelefono());
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
        if(clientesComboBoxModel.getSelectedItem()==null){
            JOptionPane.showMessageDialog(this, "Se tiene que seleccionar cliente para poder eliminar","Error",JOptionPane.ERROR_MESSAGE);       
            return;
        }else{
        int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres eliminar el expediente?", "Comprobación de eliminación.", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.NO_OPTION || opcion == JOptionPane.CLOSED_OPTION) {
                return;
            }
        }
        if(ctrl.eliminarExpediente(dto)){
            JOptionPane.showMessageDialog(this, "El expediente se eliminó correctamente","Éxito",JOptionPane.INFORMATION_MESSAGE);       
            limpiar();
            llenarComboBox();
        }
    }
    
    private void limpiar(){
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
        jScrollPane4 = new javax.swing.JScrollPane();
        areaCosmeticos = new javax.swing.JTextArea();
        comboBoxClientes = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        principal = new javax.swing.JMenuItem();
        consultas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos expedientes");
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel2.setText("Antecedentes patológicos:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 272, -1));

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel3.setText("Antecedentes personales:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 190, -1));

        jLabel4.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel4.setText("Diagnóstico de piel:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 272, -1));

        jLabel5.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel5.setText("Cosmeticos en uso:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 272, -1));

        areaPatologicos.setColumns(20);
        areaPatologicos.setRows(5);
        jScrollPane1.setViewportView(areaPatologicos);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 830, -1));

        areaPersonales.setColumns(20);
        areaPersonales.setRows(5);
        jScrollPane2.setViewportView(areaPersonales);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 830, -1));

        areaPiel.setColumns(20);
        areaPiel.setRows(5);
        jScrollPane3.setViewportView(areaPiel);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 830, -1));

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel1.setText("Nombre cliente:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 28, -1, -1));

        areaCosmeticos.setColumns(20);
        areaCosmeticos.setRows(5);
        jScrollPane4.setViewportView(areaCosmeticos);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 830, -1));

        comboBoxClientes.setModel(clientesComboBoxModel);
        jPanel2.add(comboBoxClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 830, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Expediente");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, -1));

        btnGuardar.setBackground(new java.awt.Color(204, 204, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(204, 204, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, -1, -1));

        btnEliminar.setBackground(new java.awt.Color(204, 204, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(204, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(206, 252, 245));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 3, 1010, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 550));

        menu.setText("Menú principal");

        principal.setText("Menú principal");
        principal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                principalActionPerformed(evt);
            }
        });
        menu.add(principal);

        consultas.setText("Expedientes");
        consultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultasActionPerformed(evt);
            }
        });
        menu.add(consultas);

        jMenuBar1.add(menu);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(clientesComboBoxModel.getSize()>1){
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

    private void principalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_principalActionPerformed
        dispose();
    }//GEN-LAST:event_principalActionPerformed

    private void consultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultasActionPerformed
        FrmExpedientes frm = new FrmExpedientes();
        frm.setVisible(true);
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_consultasActionPerformed

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
    private javax.swing.JComboBox<ClienteDTO> comboBoxClientes;
    private javax.swing.JMenuItem consultas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuItem principal;
    // End of variables declaration//GEN-END:variables
}
