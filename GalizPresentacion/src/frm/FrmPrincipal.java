package frm;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Paulina Cortez Alamilla
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        ajustesPantalla();

        // Crear el submenú para la etiqueta "clientes"
        JPopupMenu clientesSubMenu = new JPopupMenu();
        JMenuItem agregarClienteItem = new JMenuItem("Agregar cliente");
        JMenuItem consultarClientesItem = new JMenuItem("Consultar clientes");
        clientesSubMenu.add(agregarClienteItem);
        clientesSubMenu.add(consultarClientesItem);

        // Asociar el submenú a la etiqueta "clientes"
        clientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                clientesSubMenu.show(clientes, e.getX(), e.getY());
            }
        });

        // Agregar la etiqueta "clientes" al panel
        jPanel1.add(clientes);

        // Crear el submenú para la etiqueta "expedientes"
        JPopupMenu expedientesSubMenu = new JPopupMenu();
        JMenuItem agregarExpedienteItem = new JMenuItem("Agregar expediente");
        JMenuItem consultarExpedientesItem = new JMenuItem("Consultar expedientes");
        expedientesSubMenu.add(agregarExpedienteItem);
        expedientesSubMenu.add(consultarExpedientesItem);

        // Agregar el método oyente al item "Agregar expediente"
        agregarExpedienteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acciones a realizar cuando se selecciona "Agregar expediente"
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new FrmDatosExpedientes().setVisible(true);
                    }
                });
                System.out.println("Seleccionaste 'Agregar expediente'");
            }
        });

        // Agregar el método oyente al item "Consultar expedientes"
        consultarExpedientesItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acciones a realizar cuando se selecciona "Consultar expedientes"
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new FrmExpedientes().setVisible(true);
                    }
                });
                System.out.println("Seleccionaste 'Consultar expedientes'");
            }
        });

        // Asociar el submenú a la etiqueta "expedientes"
        expedientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                expedientesSubMenu.show(expedientes, e.getX(), e.getY());
            }
        });

        // Agregar la etiqueta "expedientes" al panel
        jPanel1.add(expedientes);

        // Crear el submenú para la etiqueta "citas"
        JPopupMenu citasSubMenu = new JPopupMenu();
        JMenuItem agregarCitaItem = new JMenuItem("Agregar cita");
        JMenuItem consultarCitaItem = new JMenuItem("Consultar cita");
        citasSubMenu.add(agregarCitaItem);
        citasSubMenu.add(consultarCitaItem);

        // Agregar el método oyente al item "Agregar cita"
        agregarCitaItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acciones a realizar cuando se selecciona "Agregar cita"
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new FrmCita().setVisible(true);
                    }
                });
                System.out.println("Seleccionaste 'Agregar cita'");
            }
        });

        // Agregar el método oyente al item "Consultar cita"
        consultarCitaItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acciones a realizar cuando se selecciona "Consultar cita"
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new DetallesCitas().setVisible(true);
                    }
                });
                System.out.println("Seleccionaste 'Consultar cita'");
            }
        });

        // Asociar el submenú a la etiqueta "citas"
        citas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                citasSubMenu.show(citas, e.getX(), e.getY());
            }
        });

        // Agregar la etiqueta "citas" al panel
        jPanel1.add(citas);

        // Crear el submenú para la etiqueta "servicios"
        JPopupMenu serviciosSubMenu = new JPopupMenu();
        JMenuItem agregarServiciosItem = new JMenuItem("Agregar servicios");
        JMenuItem consultarServiciosItem = new JMenuItem("Consultar servicios");
        serviciosSubMenu.add(agregarServiciosItem);
        serviciosSubMenu.add(consultarServiciosItem);

        // Asociar el submenú a la etiqueta "servicios"
        sevicios.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                serviciosSubMenu.show(sevicios, e.getX(), e.getY());
            }
        });

        // Agregar la etiqueta "servicios" al panel
        jPanel1.add(sevicios);

        // Crear el submenú para la etiqueta "finanzas"
        JPopupMenu finanzasSubMenu = new JPopupMenu();
        JMenuItem agregarFinanzasItem = new JMenuItem("Agregar finanza");
        JMenuItem consultarFianzasItem = new JMenuItem("Consultar finanzas");
        finanzasSubMenu.add(agregarFinanzasItem);
        finanzasSubMenu.add(consultarFianzasItem);

        // Asociar el submenú a la etiqueta "finanzas"
        finanzas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                finanzasSubMenu.show(finanzas, e.getX(), e.getY());
            }
        });

        // Agregar la etiqueta "finanzas" al panel
        jPanel1.add(finanzas);
        
        
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        sevicios = new javax.swing.JLabel();
        citas = new javax.swing.JLabel();
        finanzas = new javax.swing.JLabel();
        clientes = new javax.swing.JLabel();
        expedientes = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        Menu = new javax.swing.JMenu();
        menuExpediente = new javax.swing.JMenu();
        menuAgregarExpediente = new javax.swing.JMenuItem();
        menuConsultarExpedientes = new javax.swing.JMenuItem();
        menuCitas = new javax.swing.JMenu();
        menuAgendarCitas = new javax.swing.JMenuItem();
        menuConsultarCitas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Galiz Estudio");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.jpg"))); // NOI18N

        sevicios.setText("servicios");
        sevicios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        citas.setText("citas");
        citas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        finanzas.setText("finanzas");
        finanzas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        clientes.setText("clientes");
        clientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        expedientes.setText("expedientes");
        expedientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(expedientes, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(citas, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sevicios, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(finanzas, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(citas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(expedientes, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(clientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sevicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(finanzas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        menuBar.setBackground(new java.awt.Color(255, 216, 206));

        Menu.setBackground(new java.awt.Color(204, 255, 255));
        Menu.setBorder(null);
        Menu.setText("Menu");

        menuExpediente.setBackground(new java.awt.Color(204, 255, 255));
        menuExpediente.setText("Expedientes");

        menuAgregarExpediente.setBackground(new java.awt.Color(204, 255, 255));
        menuAgregarExpediente.setText("Agregar expediente");
        menuAgregarExpediente.setBorder(null);
        menuAgregarExpediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAgregarExpedienteActionPerformed(evt);
            }
        });
        menuExpediente.add(menuAgregarExpediente);

        menuConsultarExpedientes.setBackground(new java.awt.Color(204, 255, 255));
        menuConsultarExpedientes.setText("Consultar Expedientes");
        menuConsultarExpedientes.setBorder(null);
        menuConsultarExpedientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConsultarExpedientesActionPerformed(evt);
            }
        });
        menuExpediente.add(menuConsultarExpedientes);

        Menu.add(menuExpediente);

        menuCitas.setText("Citas");

        menuAgendarCitas.setText("Agendar cita");
        menuAgendarCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAgendarCitasActionPerformed(evt);
            }
        });
        menuCitas.add(menuAgendarCitas);

        menuConsultarCitas.setText("Consultar citas");
        menuConsultarCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConsultarCitasActionPerformed(evt);
            }
        });
        menuCitas.add(menuConsultarCitas);

        Menu.add(menuCitas);

        menuBar.add(Menu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuAgregarExpedienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAgregarExpedienteActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDatosExpedientes().setVisible(true);
            }
        });
    }//GEN-LAST:event_menuAgregarExpedienteActionPerformed

    private void menuConsultarExpedientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConsultarExpedientesActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmExpedientes().setVisible(true);
            }
        });
    }//GEN-LAST:event_menuConsultarExpedientesActionPerformed

    private void menuAgendarCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAgendarCitasActionPerformed
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCita().setVisible(true);
            }
        });
    }//GEN-LAST:event_menuAgendarCitasActionPerformed

    private void menuConsultarCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConsultarCitasActionPerformed
        // TODO add your handling code here:
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetallesCitas().setVisible(true);
            }
        });
    }//GEN-LAST:event_menuConsultarCitasActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Menu;
    private javax.swing.JLabel citas;
    private javax.swing.JLabel clientes;
    private javax.swing.JLabel expedientes;
    private javax.swing.JLabel finanzas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem menuAgendarCitas;
    private javax.swing.JMenuItem menuAgregarExpediente;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCitas;
    private javax.swing.JMenuItem menuConsultarCitas;
    private javax.swing.JMenuItem menuConsultarExpedientes;
    private javax.swing.JMenu menuExpediente;
    private javax.swing.JLabel sevicios;
    // End of variables declaration//GEN-END:variables
}
