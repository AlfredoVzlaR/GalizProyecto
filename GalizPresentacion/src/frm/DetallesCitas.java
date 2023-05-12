/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frm;

import DTO.CitasClienteConverter;
import DTO.CitasClienteDTO;
import DTO.ClienteConverter;
import DTO.ClienteDTO;
import controles.CtrlCitas;
import controles.CtrlClientes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;

/**
 *
 * @author jvale
 */
public class DetallesCitas extends javax.swing.JFrame {
    CtrlCitas ctrlCitas;
    CtrlClientes ctrlClientes;
    ClienteConverter converterCliente;
    CitasClienteConverter converterCita;
    /**
     * Creates new form DetallesCitas
     */
    public DetallesCitas() {
        initComponents();
        ctrlCitas = new CtrlCitas();
        converterCita = new CitasClienteConverter();
        ctrlClientes = new CtrlClientes();
        converterCliente = new ClienteConverter();
        llenarTablaCitas();
    }
    
    private String nombreservicio() {
        int indice = this.jTable1.getSelectedRow();
        if (indice != -1) {
            DefaultTableModel modeloTabla = (DefaultTableModel) this.jTable1.getModel();
            int indiceColumnaId = 0;
            String nombre = (String) modeloTabla.getValueAt(indice, indiceColumnaId);
            return nombre;
        } else {
            return null;
        }
    }
    private String telefono() {
        int indice = this.jTable1.getSelectedRow();
        if (indice != -1) {
            DefaultTableModel modeloTabla = (DefaultTableModel) this.jTable1.getModel();
            int indiceColumnaId = 2;
            String nombre = (String) modeloTabla.getValueAt(indice, indiceColumnaId);
            return nombre;
        } else {
            return null;
        }
    }
    
    private String fecha(){
        int indice = this.jTable1.getSelectedRow();
        if (indice != -1) {
            DefaultTableModel modeloTabla = (DefaultTableModel) this.jTable1.getModel();
            int indiceColumnaId = 1;
            String nombre = (String) modeloTabla.getValueAt(indice, indiceColumnaId);
            return nombre;
        } else {
            return null;
        }
    }
    
   private ObjectId id(){
        int indice = this.jTable1.getSelectedRow();
        if (indice != -1) {
            DefaultTableModel modeloTabla = (DefaultTableModel) this.jTable1.getModel();
            int indiceColumnaId = 3;
            ObjectId id = (ObjectId) modeloTabla.getValueAt(indice, indiceColumnaId);
            return id;
        } else {
            return null;
        }
    }
    
    private void llenarTablaCitas(){
        List<CitasClienteDTO> listaEventos  = this.ctrlCitas.consultarCitas();
        
        DefaultTableModel modeloTabla = (DefaultTableModel)this.jTable1.getModel();
        modeloTabla.setRowCount(0);
        listaEventos.forEach(evento ->
                {
                    Object[]fila = new Object[4];
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String fecha1 = dateFormat.format(evento.getFecha());
                    fila[0]=evento.getCliente().getNombre().toString();
                    fila[1]=fecha1;
                    fila[2]=evento.getCliente().getTelefono();
                    fila[3]=evento.getId();
                    modeloTabla.addRow(fila);
                });
    }
    private void llenarTablaDetalles() throws ParseException{
        ClienteDTO c = ctrlClientes.consultarCliente(telefono());
        String fecha = fecha();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = formatoFecha.parse(fecha);
        System.out.println(fecha);
        CitasClienteDTO cita = ctrlCitas.consultarCita(id());
        
        DefaultTableModel modeloTabla = (DefaultTableModel)this.jTable2.getModel();
        modeloTabla.setRowCount(0);
        for(int i =0;i<cita.getServicios().size();i++){
            Object[] fila = new Object[4];
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String fecha1 = dateFormat.format(cita.getFecha());
            fila[0] = cita.getServicios().get(i).getNombre();
            fila[1] = cita.getServicios().get(i).getDescripcion();
            fila[2] = cita.getCliente().getNombre();
            fila[3] = fecha1.toString();
            modeloTabla.addRow(fila);
        }
    }
    private void eliminarCita(){
        CitasClienteDTO citaDTO = ctrlCitas.consultarCita(id());
        
        System.out.println(citaDTO.toString());
        
        if(ctrlCitas.cancelarCita(converterCita.fromDto(citaDTO))){
            JOptionPane.showMessageDialog(this, "Se eliminó la cita correctamente","Éxito",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Hubo un error al eliminar la cita","Error",JOptionPane.ERROR_MESSAGE);
        }
        limpiarTablaDetalles();
        limpiarTablaCitas();
        llenarTablaCitas();
    }
    private void limpiarTablaDetalles(){
        DefaultTableModel modeloTabla = (DefaultTableModel)this.jTable2.getModel();
        modeloTabla.setRowCount(0);
    }
    private void limpiarTablaCitas(){
        DefaultTableModel modeloTabla = (DefaultTableModel)this.jTable1.getModel();
        modeloTabla.setRowCount(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnEliminarCita = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalles citas");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Fecha", "Teléfono cliente", "Id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre servicio", "Descripción", "Cliente", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Citas");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Detalles citas");

        btnEliminarCita.setText("Eliminar cita");
        btnEliminarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCitaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarCita))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                        .addComponent(btnEliminarCita)
                        .addContainerGap())
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed

    }//GEN-LAST:event_jTable2MousePressed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        try {
            llenarTablaDetalles();
        } catch (ParseException ex) {
            Logger.getLogger(DetallesCitas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MousePressed

    private void btnEliminarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCitaActionPerformed
        eliminarCita();
    }//GEN-LAST:event_btnEliminarCitaActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarCita;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
