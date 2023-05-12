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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

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
        ajustesPantalla();
        
        // Personalizar colores de los encabezados
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        Color colorTurquesa = new Color(196, 253, 239);
        headerRenderer.setBackground(colorTurquesa);
        headerRenderer.setForeground(Color.black);
        Font font = tablaElegidos.getTableHeader().getFont().deriveFont(Font.BOLD);
        headerRenderer.setFont(font);
        tablaElegidos.getTableHeader().setDefaultRenderer(headerRenderer);
        
        // Personalizar colores de los encabezados
        DefaultTableCellRenderer headerRenderer2 = new DefaultTableCellRenderer();
        headerRenderer2.setBackground(colorTurquesa);
        headerRenderer2.setForeground(Color.black);
        Font font2 = tablaServicios.getTableHeader().getFont().deriveFont(Font.BOLD);
        headerRenderer2.setFont(font2);
        tablaServicios.getTableHeader().setDefaultRenderer(headerRenderer2);
        
        elegidos = new ArrayList<>();
        ctrlClientes = new CtrlClientes();
        ((JTextComponent) this.fechaEvento.getDateEditor()).setEditable(false);
        ctrlCitas = new CtrlCitas();
        converterCliente = new ClienteConverter();
        ctrlServicios = new CtrlServicios();
        listaClientes = new LinkedList<>();
        listaClientes = (ctrlClientes.consultarClientes());
        llenarTablaServicios();
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

        if (elegidos.contains(ctrlServicios.consultarServicio(nombreservicio()))) {
            JOptionPane.showMessageDialog(this, "El servicio ya fue añadido");
            return;
        } else {
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

            if (!validarFecha(fechaEvento.getDate())) {
                JOptionPane.showMessageDialog(this, "La fecha de la cita debe ser igual o posterior a la fecha actual.");
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

            if (ctrlCitas.agregarCita(cita)) {
                JOptionPane.showMessageDialog(this, "Se agregó la cita correctamente");
                limpiarTablaElegidos();
                return true;
            } else {
                return false;
            }
        }
    }

    private void limpiarTablaElegidos() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaElegidos.getModel();
        modeloTabla.setRowCount(0);
        elegidos.removeAll(elegidos);
        txtHora.setText("");
        fechaEvento.setDate(new Date());
        comboBoxCliente.setSelectedIndex(-1);
    }

    private void llenarComboBox() {
        clientesComboBoxModel.addAll(listaClientes);
    }

    private Boolean validarFecha(Date date) {
        LocalDate fechaCita = date.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
        LocalDate fechaHoy = LocalDate.now();
        return fechaCita.isEqual(fechaHoy) || fechaCita.isAfter(fechaHoy);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaElegidos = new javax.swing.JTable();
        citaLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agendar citas");
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Cliente:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 70, 20));

        comboBoxCliente.setModel(clientesComboBoxModel);
        getContentPane().add(comboBoxCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 227, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Fecha:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 70, 20));

        fechaEvento.setFocusCycleRoot(true);
        getContentPane().add(fechaEvento, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 227, -1));

        try {
            txtHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txtHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 227, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Hora");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 40, 20));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 290, 40));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 610, 200));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Servicios disponibles");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(612, 20, 210, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Servicios elegidos");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, 130, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, 610, 210));

        citaLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cita.png"))); // NOI18N
        jPanel1.add(citaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 160, 120));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 580));

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
    private javax.swing.JLabel citaLabel;
    private javax.swing.JComboBox<ClienteDTO> comboBoxCliente;
    private com.toedter.calendar.JDateChooser fechaEvento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaElegidos;
    private javax.swing.JTable tablaServicios;
    private javax.swing.JFormattedTextField txtHora;
    // End of variables declaration//GEN-END:variables
}
