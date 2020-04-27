/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Jose Gpe Perez
 */
public class Registrar extends javax.swing.JFrame  {

    int staffid;
    /**
     * Creates new form FormLogin
     */
    public Registrar() {
        initComponents();
       
        setExtendedState(MAXIMIZED_BOTH);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        txtPass = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtRegNo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtRegDi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtRegCel = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jCategore = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(31, 174, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Contraseña:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 310, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 310, 40));

        txtUser.setBackground(new java.awt.Color(102, 153, 255));
        txtUser.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtUser.setForeground(new java.awt.Color(255, 255, 255));
        txtUser.setBorder(null);
        jPanel2.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 310, 40));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 310, 10));

        txtPass.setBackground(new java.awt.Color(102, 153, 255));
        txtPass.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtPass.setForeground(new java.awt.Color(255, 255, 255));
        txtPass.setBorder(null);
        jPanel2.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 310, 40));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 310, 10));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Nombre de Usuario:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 310, 40));

        txtRegNo.setBackground(new java.awt.Color(102, 153, 255));
        txtRegNo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtRegNo.setForeground(new java.awt.Color(255, 255, 255));
        txtRegNo.setBorder(null);
        txtRegNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRegNoKeyTyped(evt);
            }
        });
        jPanel2.add(txtRegNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 310, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Número de Celular:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 310, 40));

        txtRegDi.setBackground(new java.awt.Color(102, 153, 255));
        txtRegDi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtRegDi.setForeground(new java.awt.Color(255, 255, 255));
        txtRegDi.setBorder(null);
        jPanel2.add(txtRegDi, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 310, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Dirección:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 310, 40));

        txtRegCel.setBackground(new java.awt.Color(102, 153, 255));
        txtRegCel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtRegCel.setForeground(new java.awt.Color(255, 255, 255));
        txtRegCel.setBorder(null);
        txtRegCel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRegCelKeyTyped(evt);
            }
        });
        jPanel2.add(txtRegCel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 310, 40));

        btnRegistrar.setBackground(new java.awt.Color(0, 204, 204));
        btnRegistrar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrarse");
        btnRegistrar.setActionCommand("btnRegistrar");
        btnRegistrar.setBorder(null);
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 200, 40));

        btnVolver.setBackground(new java.awt.Color(0, 204, 204));
        btnVolver.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.setActionCommand("btnVolver");
        btnVolver.setBorder(null);
        btnVolver.setContentAreaFilled(false);
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVolver.setOpaque(true);
        jPanel2.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, 200, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Categorías preferidas:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 276, 160, 30));

        jCategore.setBackground(new java.awt.Color(0, 204, 204));
        jCategore.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jCategore.setForeground(new java.awt.Color(255, 255, 255));
        jCategore.setText("Categorías");
        jCategore.setActionCommand("CategPref");
        jCategore.setBorder(null);
        jCategore.setContentAreaFilled(false);
        jCategore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCategore.setOpaque(true);
        jCategore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCategoreActionPerformed(evt);
            }
        });
        jPanel2.add(jCategore, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 80, 30));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jPanel2, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRegNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRegNoKeyTyped
      char validar = evt.getKeyChar();
        if (Character.isDigit(validar)) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Debe ser un nombre sin digitos,bro.");
        }
    }//GEN-LAST:event_txtRegNoKeyTyped

    private void txtRegCelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRegCelKeyTyped
      char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "El teléfono deber ser númerico.");
        }
    }//GEN-LAST:event_txtRegCelKeyTyped

    private void jCategoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCategoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCategoreActionPerformed

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
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnRegistrar;
    public javax.swing.JButton btnVolver;
    public javax.swing.JButton jCategore;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JPasswordField txtPass;
    public javax.swing.JTextField txtRegCel;
    public javax.swing.JTextField txtRegDi;
    public javax.swing.JTextField txtRegNo;
    public javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables

   
}
