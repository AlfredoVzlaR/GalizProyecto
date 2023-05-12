/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frm;

import DTO.CitasClienteDTO;
import DTO.ClienteConverter;
import DTO.ClienteDTO;
import DTO.ServicioConverter;
import DTO.ServicioDTO;
import controles.CtrlCitas;
import controles.CtrlClientes;
import controles.CtrlServicios;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jvale
 */
public class FrmCita extends javax.swing.JFrame {

    /**
     *
     * Creates new form FrmCita
     */
    DefaultComboBoxModel<ClienteDTO> clientesComboBoxModel = new DefaultComboBoxModel<>();
    List<ClienteDTO> listaClientes = null;
    CtrlClientes ctrlClientes;
    CtrlServicios ctrlServicios;
    List<ServicioDTO> listaServicios;
    ServicioConverter converterServicio;
    List<ServicioDTO> elegidos;
    ClienteConverter converterCliente;
    CtrlCitas ctrlCitas;

    public FrmCita() {
        initComponents();
        elegidos = new ArrayList<>();
        ctrlClientes = new CtrlClientes();
        ctrlCitas = new  CtrlCitas();
        converterCliente = new ClienteConverter();
        ctrlServicios = new CtrlServicios();
        listaClientes = new LinkedList<>();
        listaClientes = (ctrlClientes.consultarClientes());
        llenarTablaServicios();
        llenarComboBox();
    }

    public DefaultComboBoxModel clientesComboBoxModel(List<ClienteDTO> listaClientes) {
        DefaultComboBoxModel<ClienteDTO> defaultComboBoxModel = new DefaultComboBoxModel<>();
        if (listaClientes != null) {
            // Para cada elemento de la Lista
            for (int i = 0; i < listaClientes.size(); i++) {
                // Agregalo a la instancia de la clase DefaultComboBoxModel
                defaultComboBoxModel.addElement(listaClientes.get(i));
            }
            return defaultComboBoxModel;
        }
        return null;
    }

    public void llenarTablaServicios() {
        listaServicios = new ArrayList<>();
        listaServicios.addAll(ctrlServicios.consultarServicios());
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaServicios.getModel();
        modeloTabla.setRowCount(0);
        listaServicios.forEach(evento
                -> {
            Object[] fila = new Object[2];
            fila[0] = evento.getNombre();
            fila[1] = evento.getCosto();
            modeloTabla.addRow(fila);
        });
    }

    private String nombreservicio() {
        int indice = this.tablaServicios.getSelectedRow();
        if (indice != -1) {
            DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaServicios.getModel();
            int indiceColumnaId = 0;
            String nombre = (String) modeloTabla.getValueAt(indice, indiceColumnaId);
            return nombre;
        } else {
            return null;
        }
    }

    public void llenarTablaElegidos() {
        ServicioDTO servicio = ctrlServicios.consultarServicio(nombreservicio());
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaElegidos.getModel();
        
        

        if(elegidos.contains(ctrlServicios.consultarServicio(nombreservicio()))){
            JOptionPane.showMessageDialog(this, "El servicio ya fue añadido");
            return;
        }else{
            elegidos.add(servicio);
        Object[] fila = new Object[2];
        fila[0] = servicio.getNombre();
        fila[1] = servicio.getCosto();
        modeloTabla.addRow(fila);
        
        }
    }

    public boolean validarHora(String hora) {
        boolean b;
        char[] a = hora.toCharArray();
        String[] c = hora.split(":");
        if ((a[0] == ' ') || (a[1] == ' ') || (a[2] == ' ') || (a[3] == ' ') || (a[4] == ' ') || (getInteger(c[0]) >= 24) || (getInteger(c[1]) > 59)) {
            b = false;
        } else {
            b = true;
        }
        return b;
    }

    public int getInteger(String valor) {
        int integer = Integer.parseInt(valor);
        return integer;
    }

    public Boolean agregarServicio() throws ParseException {

        if (clientesComboBoxModel.getSelectedItem() == null || txtHora.getText().isEmpty() || fechaEvento.getDate() == null || elegidos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Se requiere seleccionar cliente, hora y fecha y servicios");
            return false;
        } else {
            
            if(validarFecha(fechaEvento.getDate())){
                JOptionPane.showMessageDialog(this, "No se pueden agendar citas anterior a este día.");
                return false;
            }

            SimpleDateFormat plantilla = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = plantilla.format(fechaEvento.getDate());
            String hora = txtHora.getText();
            String fechaHora = fecha + " " + hora + ":00";

            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fechaHora);
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);

            Date fecha1 = Timestamp.valueOf(fechaHora);


            int dia, mes, año;

            año = Integer.parseInt(fecha.substring(0, 4));
            mes = Integer.parseInt(fecha.substring(5, 7));
            dia = Integer.parseInt(fecha.substring(8));

            int horas, minuto;
            horas = Integer.parseInt(hora.substring(0, 2));
            minuto = Integer.parseInt(hora.substring(3));

            Date dateTime = new Date(año, mes, dia, horas, minuto);

            Timestamp timestamp;
            
            CitasClienteDTO cita = new CitasClienteDTO();
            
            cita.setCliente(converterCliente.fromDto((ClienteDTO) comboBoxCliente.getSelectedItem()));
            cita.setFecha(fecha1);
            ServicioConverter serv = new ServicioConverter();
            cita.setServicios(serv.convertirLista(elegidos));
            
            if(ctrlCitas.agregarCita(cita)){
                JOptionPane.showMessageDialog(this, "Se agregó la cita correctamente");
                limpiarTablaElegidos();
                return true;
            }else{
                return false;
            }
        }
    }
    
    private void limpiarTablaElegidos(){
        DefaultTableModel modeloTabla = (DefaultTableModel)this.tablaElegidos.getModel();
        modeloTabla.setRowCount(0);
        elegidos.removeAll(elegidos);
        txtHora.setText("");
        fechaEvento.setDate(new Date());
        comboBoxCliente.setSelectedIndex(-1);
    }

    private void llenarComboBox() {
        clientesComboBoxModel.addAll(listaClientes);
    }
    
    private Boolean validarFecha(Date date){
        return date.before(new Date());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        comboBoxCliente = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        fechaEvento = new com.toedter.calendar.JDateChooser();
        txtHora = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaServicios = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaElegidos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agendar citas");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Cliente:");

        comboBoxCliente.setModel(clientesComboBoxModel);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Fecha:");

        fechaEvento.setFocusCycleRoot(true);

        try {
            txtHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Hora");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        tablaServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaServiciosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaServicios);

        tablaElegidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaElegidos);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Servicios disponibles");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Servicios elegidos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboBoxCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechaEvento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fechaEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(7, 7, 7))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar))
                .addGap(13, 13, 13))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablaServiciosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaServiciosMousePressed
        llenarTablaElegidos();
    }//GEN-LAST:event_tablaServiciosMousePressed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (this.validarHora(txtHora.getText()) == true) {
        } else {
            JOptionPane.showMessageDialog(this, "Ingresa una hora correcta", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        try {
            agregarServicio();
        } catch (ParseException ex) {
            Logger.getLogger(FrmCita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JComboBox<ClienteDTO> comboBoxCliente;
    private com.toedter.calendar.JDateChooser fechaEvento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaElegidos;
    private javax.swing.JTable tablaServicios;
    private javax.swing.JFormattedTextField txtHora;
    // End of variables declaration//GEN-END:variables
}
