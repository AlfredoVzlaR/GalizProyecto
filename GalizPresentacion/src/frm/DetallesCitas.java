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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
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
        ajustesPantalla();
        
        // Personalizar colores de los encabezados
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        Color colorTurquesa = new Color(196, 253, 239);
        headerRenderer.setBackground(colorTurquesa);
        headerRenderer.setForeground(Color.black);
        Font font = jTable1.getTableHeader().getFont().deriveFont(Font.BOLD);
        headerRenderer.setFont(font);
        jTable1.getTableHeader().setDefaultRenderer(headerRenderer);
        
        // Personalizar colores de los encabezados
        DefaultTableCellRenderer headerRenderer2 = new DefaultTableCellRenderer();
        headerRenderer2.setBackground(colorTurquesa);
        headerRenderer2.setForeground(Color.black);
        Font font2 = jTable2.getTableHeader().getFont().deriveFont(Font.BOLD);
        headerRenderer2.setFont(font2);
        jTable2.getTableHeader().setDefaultRenderer(headerRenderer2);
        
        btnEliminarCita.setOpaque(true);
        btnEliminarCita.setBackground(colorTurquesa);
        
        ctrlCitas = new CtrlCitas();
        ((JTextComponent) this.jDateChooser1.getDateEditor()).setEditable(false);
        converterCita = new CitasClienteConverter();
        ctrlClientes = new CtrlClientes();
        converterCliente = new ClienteConverter();
        llenarTablaCitas();
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
        List<CitasClienteDTO> listaEventos  = this.ctrlCitas.consultarCitasHoy();
        
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
    private void llenarPosteriores(){
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
    private void llenarTodas(){
        List<CitasClienteDTO> listaEventos  = this.ctrlCitas.consultarTodas();
        
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
    private void llenarPorFecha(){
        
        if(jDateChooser1.getDate()==null){
            JOptionPane.showMessageDialog(this, "Debes ingresar una fecha","Alerta",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        List<CitasClienteDTO> listaEventos  = this.ctrlCitas.consultarCitasFecha(jDateChooser1.getDate());
        
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        btnTodas = new javax.swing.JButton();
        btnConsultarTodas = new javax.swing.JButton();
        btnPorFecha = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnEliminarCita = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalles citas");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 441, 393));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 460, 260));

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 123, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Detalles citas");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, 84, -1));

        btnTodas.setText("Todas");
        btnTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodasActionPerformed(evt);
            }
        });
        jPanel1.add(btnTodas, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, -1, -1));

        btnConsultarTodas.setText("Posteriores");
        btnConsultarTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarTodasActionPerformed(evt);
            }
        });
        jPanel1.add(btnConsultarTodas, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        btnPorFecha.setText("Fecha");
        btnPorFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPorFechaActionPerformed(evt);
            }
        });
        jPanel1.add(btnPorFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 61, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Citas");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 37, 20));

        btnEliminarCita.setText("Eliminar cita");
        btnEliminarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCitaActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminarCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 410, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 580));

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

    private void btnConsultarTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarTodasActionPerformed
        limpiarTablaDetalles();
        llenarPosteriores();
    }//GEN-LAST:event_btnConsultarTodasActionPerformed

    private void btnTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodasActionPerformed
        limpiarTablaDetalles();
        llenarTodas();
    }//GEN-LAST:event_btnTodasActionPerformed

    private void btnPorFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPorFechaActionPerformed
        llenarPorFecha();
    }//GEN-LAST:event_btnPorFechaActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultarTodas;
    private javax.swing.JButton btnEliminarCita;
    private javax.swing.JButton btnPorFecha;
    private javax.swing.JButton btnTodas;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
