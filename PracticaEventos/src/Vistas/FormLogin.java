
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

public class FormLogin extends javax.swing.JFrame  {
  
    /**
     * Creates new form FormLogin
     */
    public FormLogin() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        salir.setLocation(this.WIDTH,this.HEIGHT);
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
        Registrar = new javax.swing.JButton();
        Iniciar = new javax.swing.JButton();
        btnAnon = new javax.swing.JButton();
        salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(31, 174, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Contraseña:");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 310, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Usuario:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 310, 40));

        txtUser.setBackground(new java.awt.Color(102, 153, 255));
        txtUser.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtUser.setForeground(new java.awt.Color(255, 255, 255));
        txtUser.setBorder(null);
        jPanel2.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 310, 40));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 310, 10));

        txtPass.setBackground(new java.awt.Color(102, 153, 255));
        txtPass.setBorder(null);
        txtPass.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtPass.setForeground(new java.awt.Color(255, 255, 255));
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });
        jPanel2.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 310, 40));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 310, 10));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_location_120px.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 350, 120));

        Registrar.setText("Registrar");
        Registrar.setBackground(new java.awt.Color(0, 204, 204));
        Registrar.setBorder(null);
        Registrar.setContentAreaFilled(false);
        Registrar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Registrar.setForeground(new java.awt.Color(255, 255, 255));
        Registrar.setOpaque(true);
        jPanel2.add(Registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 160, 30));

        Iniciar.setText("Entrar");
        Iniciar.setActionCommand("Iniciar");
        Iniciar.setBackground(new java.awt.Color(0, 204, 204));
        Iniciar.setBorder(null);
        Iniciar.setContentAreaFilled(false);
        Iniciar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Iniciar.setForeground(new java.awt.Color(255, 255, 255));
        Iniciar.setOpaque(true);
        jPanel2.add(Iniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 160, 30));

        btnAnon.setBackground(new java.awt.Color(255, 255, 255));
        btnAnon.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAnon.setForeground(new java.awt.Color(0, 204, 204));
        btnAnon.setText("Entrar como Anónimo");
        btnAnon.setActionCommand("btnAnon");
        btnAnon.setBorder(null);
        jPanel2.add(btnAnon, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 439, 160, 30));

        jPanel1.add(jPanel2, new java.awt.GridBagConstraints());

        salir.setText("jButton1");
        salir.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        jPanel1.add(salir, new java.awt.GridBagConstraints());

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

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed

    private void btnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistroActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
       System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Iniciar;
    public javax.swing.JButton Registrar;
    public javax.swing.JButton btnAnon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton salir;
    public javax.swing.JPasswordField txtPass;
    public javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables

   
}
