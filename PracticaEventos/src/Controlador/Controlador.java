/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vistas.FormLogin;
import Vistas.FormMenuPrincipal;
import Vistas.Registrar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import practicaeventos.Evento;
import practicaeventos.Usuario;
import practicaeventos.proxyInterface;

/**
 *
 * @author jopul
 */
public class Controlador implements ActionListener {
    private Evento e;
    private FormMenuPrincipal men;
    private FormLogin log;
    private Registrar reg;
    private proxyInterface model;
    private Usuario u;

    public Controlador(FormLogin log, Registrar reg, FormMenuPrincipal men, proxyInterface model) throws Exception {
        this.men = men;
        this.model = model;
        this.log = log;
        this.reg = reg;

        log.txtUser.addActionListener(this);
        log.txtPass.addActionListener(this);
        log.Registrar.addActionListener(this);
        log.Iniciar.addActionListener(this);
        ///
        reg.txtUser.addActionListener(this);
        reg.txtPass.addActionListener(this);
        reg.btnRegistrar.addActionListener(this);
        reg.btnVolver.addActionListener(this);
        
        men.crNom.addActionListener(this);
        men.crDir.addActionListener(this);
        men.crDes.addActionListener(this);
        ///Categorías
        men.espectaculos.addActionListener(this);
        men.educacion.addActionListener(this);
        men.cultura.addActionListener(this);
        men.deportes.addActionListener(this);
        men.gastronomia.addActionListener(this);
        men.musica.addActionListener(this);
        men.cine.addActionListener(this);
        men.infantil.addActionListener(this);
        men.religion.addActionListener(this);
        men.teatro.addActionListener(this);
        men.taller.addActionListener(this);
        men.social.addActionListener(this);
        ///Cambios 
        men.btnCerrarS.addActionListener(this);
        men.btnCat.addActionListener(this);
        men.btnCrear.addActionListener(this);
        men.btnVerE.addActionListener(this);
        men.btnMis.addActionListener(this);
        //EVENTO 
        men.btnCom.addActionListener(this);
        men.btnRep.addActionListener(this);

    }

    public void Ocultar(String panel) {
        men.pnEvento.setVisible(false);
        men.pnReportes.setVisible(false);
        men.pnREvento.setVisible(false);
        men.pnEvento1.setVisible(false);
        men.pnlinicio.setVisible(false);
        men.pnComentarios.setVisible(false);

        switch (panel) {
            case "eventos":
                men.pnEvento.setVisible(true);
                break;
            case "miseventos":
                men.pnEvento.setVisible(true);
                break;
            case "comentarios":
                men.pnComentarios.setVisible(true);
                break;
            case "reportes":
                men.pnReportes.setVisible(true);
                break;
                case "categorias":
                men.pnlinicio.setVisible(true);
                break;
                case "crearE":
                    men.pnREvento.setVisible(true);
                    break;

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel dm = (DefaultTableModel) men.jEventos.getModel();
        try {
            System.out.println(e);
            String command = e.getActionCommand();
            System.out.println(command);
            switch (command) {
                ///VISTA LOGIN
                
                case "Registrar":
                    log.dispose();
                    reg.setVisible(true);
                    break;
                case "Iniciar":
                    boolean aprobado = model.VerificarUsuario(log.txtUser.getText(), String.valueOf(log.txtPass.getPassword()));
                    if (aprobado == true) {
                        u.setNombre(log.txtUser.getText());
                        log.dispose();
                        men.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(log, "Usuario/Contraseña incorrectos");
                    }
                    break;
                ///VISTA REGISTRO
                case "btnVolver":
                    reg.dispose();
                    log.setVisible(true);
                    break;
                case "btnRegistrar":
                    String nombreE = reg.txtUser.getText();
                    String pass = reg.txtPass.getPassword().toString();
                    String name = reg.txtRegNo.getText();
                    String dirE = reg.txtRegDi.getText();
                    String cel = reg.txtRegCel.getText();
//                    Evento e = new Evento();
                     boolean exito = model.RegistrarUsuario(nombreE, pass,name,dirE,cel);
                   if (exito == true) {
                        JOptionPane.showMessageDialog(reg,"Exito al registrar usuario.");
                     } else {
                     JOptionPane.showMessageDialog(reg, "Error al registrarse");
                     }
                    break;
                //VISTA PRINCIPAL
                // CATEGORÍAS
                case "espectaculos":
                    Ocultar("eventos");
                    // DefaultTableModel dm = (DefaultTableModel) men.jEventos.getModel();
                    dm.getDataVector().removeAllElements();
                    dm.fireTableDataChanged();
                    ResultSet rs = model.FiltrarEventos("Espectaculo");
                    while (rs.next()) {
                        String title = rs.getString(3);
                        String description = rs.getString(4);
                        String date = rs.getString(5);
                        String hora = rs.getString(6);

                        dm.addRow(new Object[]{title, description, date, hora});
                    }
                    rs.close();
                    break;
                case "teatro":
                    Ocultar("eventos");

                    dm.getDataVector().removeAllElements();
                    dm.fireTableDataChanged();
                    ResultSet rs1 = model.FiltrarEventos("Teatro");
                    while (rs1.next()) {
                        String title = rs1.getString(3);
                        String description = rs1.getString(4);
                        String date = rs1.getString(5);
                        String hora = rs1.getString(6);

                        dm.addRow(new Object[]{title, description, date, hora});
                    }
                    rs1.close();
                    break;
                case "educacion":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    dm.fireTableDataChanged();
                    ResultSet rs2 = model.FiltrarEventos("Educacion");
                    while (rs2.next()) {
                        String title = rs2.getString(3);
                        String description = rs2.getString(4);
                        String date = rs2.getString(5);
                        String hora = rs2.getString(6);

                        dm.addRow(new Object[]{title, description, date, hora});
                    }
                    rs2.close();
                    break;
                case "cultura":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    dm.fireTableDataChanged();
                    ResultSet rs3 = model.FiltrarEventos("Cultura");
                    while (rs3.next()) {
                        String title = rs3.getString(3);
                        String description = rs3.getString(4);
                        String date = rs3.getString(5);
                        String hora = rs3.getString(6);

                        dm.addRow(new Object[]{title, description, date, hora});
                    }
                    rs3.close();
                    break;
                case "gastronomia":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    dm.fireTableDataChanged();
                    ResultSet rs4 = model.FiltrarEventos("Gastronomia");
                    while (rs4.next()) {
                        String title = rs4.getString(3);
                        String description = rs4.getString(4);
                        String date = rs4.getString(5);
                        String hora = rs4.getString(6);

                        dm.addRow(new Object[]{title, description, date, hora});
                    }
                    rs4.close();
                    break;
                case "musica":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    dm.fireTableDataChanged();
                    ResultSet rs5 = model.FiltrarEventos("Musica");
                    while (rs5.next()) {
                        String title = rs5.getString(3);
                        String description = rs5.getString(4);
                        String date = rs5.getString(5);
                        String hora = rs5.getString(6);

                        dm.addRow(new Object[]{title, description, date, hora});
                    }
                    rs5.close();
                    break;
                case "social":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    dm.fireTableDataChanged();
                    ResultSet rs6 = model.FiltrarEventos("Social");
                    while (rs6.next()) {
                        String title = rs6.getString(3);
                        String description = rs6.getString(4);
                        String date = rs6.getString(5);
                        String hora = rs6.getString(6);

                        dm.addRow(new Object[]{title, description, date, hora});
                    }
                    rs6.close();
                    break;
                case "cine":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    dm.fireTableDataChanged();
                    ResultSet rs7 = model.FiltrarEventos("Cine");
                    while (rs7.next()) {
                        String title = rs7.getString(3);
                        String description = rs7.getString(4);
                        String date = rs7.getString(5);
                        String hora = rs7.getString(6);

                        dm.addRow(new Object[]{title, description, date, hora});
                    }
                    rs7.close();
                    break;
                case "taller":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    dm.fireTableDataChanged();
                    ResultSet rs8 = model.FiltrarEventos("Taller");
                    while (rs8.next()) {
                        String title = rs8.getString(3);
                        String description = rs8.getString(4);
                        String date = rs8.getString(5);
                        String hora = rs8.getString(6);

                        dm.addRow(new Object[]{title, description, date, hora});
                    }
                    rs8.close();
                    break;
                case "infantil":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    dm.fireTableDataChanged();
                    ResultSet rs9 = model.FiltrarEventos("Infantil");
                    while (rs9.next()) {
                        String title = rs9.getString(3);
                        String description = rs9.getString(4);
                        String date = rs9.getString(5);
                        String hora = rs9.getString(6);

                        dm.addRow(new Object[]{title, description, date, hora});
                    }
                    rs9.close();
                    break;
                case "deportes":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    dm.fireTableDataChanged();
                    ResultSet rsa1 = model.FiltrarEventos("Deportes");
                    while (rsa1.next()) {
                        String title = rsa1.getString(3);
                        String description = rsa1.getString(4);
                        String date = rsa1.getString(5);
                        String hora = rsa1.getString(6);

                        dm.addRow(new Object[]{title, description, date, hora});
                    }
                    rsa1.close();
                    break;
                case "religion":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    dm.fireTableDataChanged();
                    ResultSet rsa2 = model.FiltrarEventos("Religion");
                    while (rsa2.next()) {
                        String title = rsa2.getString(3);
                        String description = rsa2.getString(4);
                        String date = rsa2.getString(5);
                        String hora = rsa2.getString(6);

                        dm.addRow(new Object[]{title, description, date, hora});
                    }
                    rsa2.close();
                    break;
                /////TERMINA CATEGORÍAS
                ///INICIA MOVIMIENTO DE PANELES
                case "btnVerE":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    dm.fireTableDataChanged();
                    ResultSet rsb = model.ObtenerEventos();
                    while (rsb.next()) {
                        String title = rsb.getString(3);
                        String description = rsb.getString(4);
                        String date = rsb.getString(5);
                        String hora = rsb.getString(6);

                        dm.addRow(new Object[]{title, description, date, hora});
                    }
                    break;
                case "btnCat":
                    Ocultar("categorias");
                    break;
                case "btnMis":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    dm.fireTableDataChanged();
                    ResultSet rsc = model.MisEventos(u.getNombre());
                    while (rsc.next()) {
                        String title = rsc.getString(3);
                        String description = rsc.getString(4);
                        String date = rsc.getString(5);
                        String hora = rsc.getString(6);

                        dm.addRow(new Object[]{title, description, date, hora});
                    }
                    break;
                case "btnCrear":
                    Ocultar("crearE");
                    break;
                case "btnCerrarS":
                    men.dispose();
                    log.setVisible(true);
                    break;
                    
                case "btnCom":
                    Ocultar("comentarios");
                    men.areaCom.setText("");
                    ArrayList comentarios = model.ObtenerComentarios();
                    for (int i = 0; i < comentarios.size(); i++) {
                        men.areaCom.append(comentarios.get(i).toString()+"\n");     
                    }
                     
                    break;
                case "btnRep":
                    Ocultar("reportes");
                    men.areaRep.setText("");
                    ArrayList reportes = model.ObtenerReporte();
                    for (int i = 0; i < reportes.size(); i++) {
                        men.areaRep.append(reportes.get(i).toString()+"\n");     
                    }
                    break;
                case "btnEnviarCo":
                    
                    men.areaCom.setText("");
                    String mensaje =  men.mensaje.getText();
                    model.RegistrarComentarios(e.getID(), u.getUserid(), mensaje);
                    ArrayList comentarios2 = model.ObtenerComentarios();
                    for (int i = 0; i < comentarios2.size(); i++) {
                        men.areaCom.append(comentarios2.get(i).toString()+"\n");     
                    }
                    break;
                    
                    
                    
                    

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (RemoteException ex) {
            System.out.println(ex);
        }
    }

}
