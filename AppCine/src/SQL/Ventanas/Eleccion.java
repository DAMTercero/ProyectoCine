/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Ventanas;

import SQL.Dao.EmpleadoFunciones;
import SQL.Dao.HistoricoFunciones;
import SQL.Dao.PeliculaFunciones;
import SQL.Dao.SalaFunciones;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author morfe
 */
public class Eleccion extends javax.swing.JFrame {

    //ventanas
    public Main ventanaMain;
    public AMB ventanaAMB;
    public Historico ventanaHistorico;
    //archivos funciones
    public PeliculaFunciones peliculaFunciones = new PeliculaFunciones();
    public SalaFunciones salaFunciones = new SalaFunciones();
    public EmpleadoFunciones empleadoFunciones = new EmpleadoFunciones();
    public HistoricoFunciones historicoFunciones = new HistoricoFunciones();

    //Si soy ventana MySql o SQlite
    private boolean soyMySql;

    /**
     * Creates new form Eleccion
     */
    public Eleccion() {
        initComponents();
        this.setLocationRelativeTo(null);//centrar la pantalla
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonSalas = new javax.swing.JButton();
        botonEmpleado = new javax.swing.JButton();
        botonPelis = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        botonHistorico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        botonSalas.setFont(new java.awt.Font("Magneto", 1, 24)); // NOI18N
        botonSalas.setText("Salas");
        botonSalas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalasActionPerformed(evt);
            }
        });

        botonEmpleado.setFont(new java.awt.Font("Magneto", 1, 24)); // NOI18N
        botonEmpleado.setText("Empleados");
        botonEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEmpleadoActionPerformed(evt);
            }
        });

        botonPelis.setFont(new java.awt.Font("Magneto", 1, 24)); // NOI18N
        botonPelis.setText("Películas");
        botonPelis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPelisActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Magneto", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("¡Bienvenido!");

        botonHistorico.setFont(new java.awt.Font("Magneto", 1, 24)); // NOI18N
        botonHistorico.setText("Registros");
        botonHistorico.setActionCommand("Empleados");
        botonHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonHistoricoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botonHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonSalas, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(botonPelis, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonSalas, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonPelis, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botonHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSalasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalasActionPerformed
        if (ventanaAMB == null) {
            ventanaAMB = new SQL.Ventanas.AMB();
            ventanaAMB.ventanaEleccion = this;// Pasarle este ventanaEleccion
        }
        ventanaAMB.salaFunciones = this.salaFunciones;//pasarle funciones
        salaFunciones.abrirVentanaSalas(ventanaAMB);//pasarlo a la clase de las funciones de esa sala
        ventanaAMB.setVisible(true);//ver ventana
        ventanaAMB.setSoyMySql(soyMySql);//pasarle tipo de base de datos
        this.setEnabled(false);//deshabilitar esta para no abrir de mas

        System.out.println(ventanaAMB.isSoyMySql());
    }//GEN-LAST:event_botonSalasActionPerformed

    private void botonPelisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPelisActionPerformed
        if (ventanaAMB == null) {
            ventanaAMB = new SQL.Ventanas.AMB();
            ventanaAMB.ventanaEleccion = this;// Pasarle este ventanaEleccion
        }
        ventanaAMB.peliculaFunciones = this.peliculaFunciones;//pasarle funciones
        peliculaFunciones.abrirVentanaPelis(ventanaAMB);//pasarlo a la clase de las funciones de esa sala
        ventanaAMB.setVisible(true);//ver ventana
        ventanaAMB.setSoyMySql(soyMySql);//pasarle tipo de base de datos
        this.setEnabled(false);//deshabilitar esta para no abrir de mas

        System.out.println(ventanaAMB.isSoyMySql());
    }//GEN-LAST:event_botonPelisActionPerformed

    private void botonEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEmpleadoActionPerformed
        if (ventanaAMB == null) {
            ventanaAMB = new SQL.Ventanas.AMB();
            ventanaAMB.ventanaEleccion = this;// Pasarle este ventanaEleccion
        }
        ventanaAMB.empleadoFunciones = this.empleadoFunciones;//pasarle funciones
        empleadoFunciones.abrirVentanaEmpleados(ventanaAMB);//pasarlo a la clase de las funciones de esa sala
        ventanaAMB.setVisible(true);//ver ventana
        ventanaAMB.setSoyMySql(soyMySql);//pasarle tipo de base de datos
        this.setEnabled(false);//deshabilitar esta para no abrir de mas        

        System.out.println(ventanaAMB.isSoyMySql());

    }//GEN-LAST:event_botonEmpleadoActionPerformed

    private void botonHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonHistoricoActionPerformed
        if (ventanaHistorico == null) {
            ventanaHistorico = new SQL.Ventanas.Historico();
            ventanaHistorico.ventanaEleccion = this;// Pasarle este ventanaEleccion
        }
        ventanaHistorico.historicoFunciones = this.historicoFunciones;//pasarle funciones
        try {
            historicoFunciones.abrirVentanaHistorico(ventanaHistorico);//pasarlo a la clase de las funciones de esa sala
        } catch (IOException ex) {
            Logger.getLogger(Eleccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Eleccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Eleccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        ventanaHistorico.setVisible(true);//ver ventana
        ventanaHistorico.setSoyMySql(soyMySql);//pasarle tipo de base de datos
        this.setEnabled(false);//deshabilitar esta para no abrir de mas        
    }//GEN-LAST:event_botonHistoricoActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        //volver a atras, dejando la anterior disponible OJO que se usa dispose por si se quiere acceder a otro local (bbdd)
        ventanaMain.setEnabled(true);
        ventanaMain.toFront();
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(Eleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Eleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Eleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Eleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Eleccion().setVisible(true);
            }
        });
    }

    /**
     *
     * @return devuelve true o false en funcion de sql o mysql
     */
    public boolean isSoyMySql() {
        return soyMySql;
    }

    /**
     *
     * @param soyMySql meter true o false en funcion de sql o mysql
     */
    public void setSoyMySql(boolean soyMySql) {
        this.soyMySql = soyMySql;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton botonEmpleado;
    public static javax.swing.JButton botonHistorico;
    public static javax.swing.JButton botonPelis;
    public static javax.swing.JButton botonSalas;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
