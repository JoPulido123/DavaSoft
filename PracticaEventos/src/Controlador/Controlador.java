/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vistas.FormLogin;
import Vistas.FormMenuPrincipal;
import Vistas.Registrar;
import com.teamdev.jxbrowser.browser.Browser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.RemoteException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import practicaeventos.Evento;
import java.awt.BorderLayout;
import practicaeventos.Usuario;
import practicaeventos.proxyInterface;
import static com.teamdev.jxbrowser.engine.RenderingMode.*;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author jopul
 */
public class Controlador implements ActionListener, Serializable {

    private Evento ev = new Evento();
    private FormMenuPrincipal men;
    private FormLogin log;
    private Registrar reg;
    private proxyInterface model;
    private Usuario u = new Usuario();
    String usuarioactual = null;
    final JFXPanel fxpanel = new JFXPanel();
    ///////////////////////////////////////////////////////////////////// 
    //EngineOptions options
    //      = EngineOptions.newBuilder(HARDWARE_ACCELERATED).build();
    // Engine engine = Engine.newInstance(options);

// Create the Browser
    //Browser browser = engine.newBrowser();
    // BrowserView view = BrowserView.newInstance(browser);
    //////////////////////////////////////////////////
    public Controlador(FormLogin log, Registrar reg, FormMenuPrincipal men, proxyInterface model) throws Exception {
        this.men = men;
        this.model = model;
        this.log = log;
        this.reg = reg;
        DefaultTableModel dm = (DefaultTableModel) men.jEventos.getModel();
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
        men.btnModi.addActionListener(this);
        men.btnSig.addActionListener(this);
        //EVENTO 
        men.Undo.addActionListener(this);
        men.Undo1.addActionListener(this);
        men.btnCom.addActionListener(this);
        men.btnRep.addActionListener(this);
        men.btnCE.addActionListener(this);
        log.btnAnon.addActionListener(this);
        ListSelectionModel modelo = men.jEventos.getSelectionModel();
        //PRUEBA 1
        men.jEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    try {
                       int linea = table.getSelectedRow();
                       if(linea==-1){
                           JOptionPane.showMessageDialog(men,"Seleccione un evento");
                       }else{
                           int event = Integer.parseInt((String) table.getValueAt(linea, 0));
                        //Aca llamamos a la ventana que nos traera el los detalles del registro
                        Ocultar("evento");
                        men.nombre1.setText("");
                        men.desc1.setText("");
                        men.fecha1.setText("");
                        men.hora1.setText("");
                        //int selectedrow = men.jEventos.getSelectedRow();
                        //int even = Integer.parseInt((String) men.jEventos.getValueAt(selectedrow, 0));
                        int part = model.Participantes(event);
                        System.out.println(part);
                        Evento aa = model.Informacion(event);
                        ev.setEventid(event);
                        String nombre = aa.getNomevento();
                        String descp = aa.getDescripcion();
                        String address = aa.getDireccion().replace(" ", "+");
                        String date = aa.getFecha().toString();
                        String time = aa.getHora().toString();
                        men.nombre1.setText(nombre);
                        men.desc1.setText(descp);
                        men.part1.setText(part + "");
                        men.fecha1.setText(date);
                        men.hora1.setText(time);
                       }
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        men.btnEnviarCo.addActionListener(this);
        men.btnEnviarRep.addActionListener(this);
        men.btnCom1.addActionListener(this);
        men.btnCom2.addActionListener(this);
        men.btnDelete.addActionListener(this);
        men.btnModi.addActionListener(this);
        men.jEvD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    int seguro = JOptionPane.showConfirmDialog(men, "¿Desea eliminar este evento?", "Advertencia", JOptionPane.YES_NO_OPTION);
                    if (seguro == JOptionPane.YES_OPTION) {
                        int linea = table.getSelectedRow();
                        int event = Integer.parseInt((String) table.getValueAt(linea, 0));
                        boolean exito = model.BorrarEvento(event);
                        if (exito == true) {
                            JOptionPane.showMessageDialog(men, "Evento borrado");
                        } else {
                            JOptionPane.showMessageDialog(men, "Error al querer borrar el evento.");
                        }
                    }
                }
            }
        });
////
        men.jMod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    int seguro = JOptionPane.showConfirmDialog(men, "¿Desea modificar este evento?", "Advertencia", JOptionPane.YES_NO_OPTION);
                    if (seguro == JOptionPane.YES_OPTION) {
                        try {
                            int linea = table.getSelectedRow();
                            int event = Integer.parseInt((String) table.getValueAt(linea, 0));
                            String nombre = table.getValueAt(linea, 1).toString();
                            String desc = table.getValueAt(linea, 2).toString();
                            String address = table.getValueAt(linea, 3).toString();
                            String date = table.getValueAt(linea, 4).toString();
                            String tiempo = table.getValueAt(linea, 5).toString();
                            Date fecha = Date.valueOf(date);
                            Time time = Time.valueOf(tiempo);

                            boolean exito = model.ModificarEvento(event, nombre, desc, address, fecha, time);
                            if (exito == true) {
                                JOptionPane.showMessageDialog(men, "Evento modificado");
                            } else {
                                JOptionPane.showMessageDialog(men, "Error al modificar el evento,favor de no alterar el id.");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
    }

    public void Ocultar(String panel) {
        men.pnEvento.setVisible(false);
        men.pnReportes.setVisible(false);
        men.pnREvento.setVisible(false);
        men.pnEvento1.setVisible(false);
        men.pnlinicio.setVisible(false);
        men.pnComentarios.setVisible(false);
        men.pnEventoD.setVisible(false);
        men.pnEventoM.setVisible(false);

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

            case "evento":
                men.pnEvento1.setVisible(true);
                break;

            case "borrar":
                men.pnEventoD.setVisible(true);
                break;
            case "modificar":
                men.pnEventoM.setVisible(true);
                break;

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // Usuario u = null;

        //Evento ev = null;
        DefaultTableModel dm = (DefaultTableModel) men.jEventos.getModel();
        DefaultTableModel dd = (DefaultTableModel) men.jEvD.getModel();
        DefaultTableModel de = (DefaultTableModel) men.jMod.getModel();

        try {
            System.out.println(e);
            String command = e.getActionCommand();
            System.out.println(command);
            switch (command) {
                ///VISTA LOGIN
                case "btnAnon":
                    log.dispose();
                    men.setVisible(true);
                    men.btnCrear.setVisible(false);
                    men.btnMis.setVisible(false);
                    men.btnCom1.setVisible(false);
                    men.btnCom2.setVisible(false);
                    men.btnEnviarCo.setVisible(false);
                    men.btnEnviarRep.setVisible(false);
                    men.btnDelete.setVisible(false);
                    men.btnSig.setVisible(false);
                    men.labelC.setVisible(false);
                    men.labeld.setVisible(false);
                    men.textCausa.setVisible(false);
                    men.textDesc.setVisible(false);
                  
                    men.btnModi.setVisible(false);
                    break;
                case "Undo":
                    men.textCausa.setText("");
                    men.textDesc.setText("");
                    men.mensaje.setText("");
                    Ocultar("evento");
                    break;

                case "Registrar":
                    log.txtPass.setText("");
                    log.txtUser.setText("");
                    log.dispose();
                    reg.setVisible(true);
                    break;
                case "Iniciar":
                    String user = log.txtUser.getText();
                    String password = String.valueOf(log.txtPass.getPassword());
                    System.out.println(user);
                    boolean aprobado = model.VerificarUsuario(user, password);
                    if (aprobado == true) {
                        men.btnCrear.setVisible(true);
                        men.btnMis.setVisible(true);
                        men.btnCom1.setVisible(true);
                        men.btnCom2.setVisible(true);
                        men.btnEnviarCo.setVisible(true);
                        men.btnEnviarRep.setVisible(true);
                        men.btnDelete.setVisible(true);
                        men.btnModi.setVisible(true);
                        men.btnSig.setVisible(true);
                        men.labelC.setVisible(true);
                    men.labeld.setVisible(true);
                    men.textCausa.setVisible(true);
                    men.textDesc.setVisible(true);
                        log.txtUser.setText("");
                        log.txtPass.setText("");
                        System.out.println(user + "2");
                        usuarioactual = user;
                        u.setUserid(user);
                        //System.out.println(u.getNombre());
                        log.dispose();
                        men.setVisible(true);
                    } else if (aprobado == false) {
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
                    String pass = String.valueOf(reg.txtPass.getPassword());
                    String name = reg.txtRegNo.getText();
                    String dirE = reg.txtRegDi.getText();
                    String cel = reg.txtRegCel.getText();
//                    Evento e = new Evento();
                    boolean exito = model.RegistrarUsuario(nombreE, pass, name, dirE, cel);
                    if (exito == true) {
                        reg.txtUser.setText("");
                        reg.txtPass.setText("");
                        reg.txtRegDi.setText("");
                        reg.txtRegCel.setText("");
                        reg.txtRegNo.setText("");
                        JOptionPane.showMessageDialog(reg, "Exito al registrar usuario.");
                    } else {
                        JOptionPane.showMessageDialog(reg, "Error al registrarse");
                    }
                    break;

                case "btnDelete":
                    Ocultar("borrar");
                    dd.getDataVector().removeAllElements();
                    // dm.fireTableDataChanged();
                    System.out.println();
                    //SerRS rsc1 = model.MisEventos(u.getNombre());
                    System.out.println(usuarioactual);
                    List<Evento> rscc = model.MisEventos(usuarioactual);
                    System.out.println(u.getNombre());
                    for (int i = 0; i < rscc.size(); i++) {
                        String id = String.valueOf(rscc.get(i).getEventid());
                        String title = rscc.get(i).getNomevento();
                        String description = rscc.get(i).getDescripcion();
                        String date = String.valueOf(rscc.get(i).getFecha());
                        String hora = String.valueOf(rscc.get(i).getHora());
                        dd.addRow(new Object[]{id, title, description, date, hora});
                    }
                    break;
                //VISTA PRINCIPAL
                case "btnModi":
                    Ocultar("modificar");
                    de.getDataVector().removeAllElements();
                    // dm.fireTableDataChanged();
                    System.out.println();
                    //SerRS rsc1 = model.MisEventos(u.getNombre());
                    System.out.println(usuarioactual);
                    List<Evento> rscm = model.MisEventos(usuarioactual);
                    System.out.println(u.getNombre());
                    for (int i = 0; i < rscm.size(); i++) {
                        String id = String.valueOf(rscm.get(i).getEventid());
                        String title = rscm.get(i).getNomevento();
                        String description = rscm.get(i).getDescripcion();
                        String address = rscm.get(i).getDireccion();
                        String date = String.valueOf(rscm.get(i).getFecha());
                        String hora = String.valueOf(rscm.get(i).getHora());
                        de.addRow(new Object[]{id, title, description, address, date, hora});
                    }
                    break;
                // CATEGORÍAS
                case "espectaculos":

                    Ocultar("eventos");
                    // DefaultTableModel dm = (DefaultTableModel) men.jEventos.getModel();
                    dm.getDataVector().removeAllElements();

                    System.out.println("Llego al especto");
                    //SerRS rsz = model.FiltrarEventos("Espectaculo");
                    List<Evento> rs = model.FiltrarEventos("Espectaculos");
                    for (int i = 0; i < rs.size(); i++) {
                        String id = String.valueOf(rs.get(i).getEventid());
                        String title = rs.get(i).getNomevento();
                        String description = rs.get(i).getDescripcion();
                        String date = String.valueOf(rs.get(i).getFecha());
                        String hora = String.valueOf(rs.get(i).getHora());
                        dm.addRow(new Object[]{id, title, description, date, hora});
                    }
                    break;
                case "teatro":
                    Ocultar("eventos");

                    dm.getDataVector().removeAllElements();
                    // dm.fireTableDataChanged();
                    List<Evento> rs1 = model.FiltrarEventos("Teatro");
                    for (int i = 0; i < rs1.size(); i++) {
                        String id = String.valueOf(rs1.get(i).getEventid());
                        String title = rs1.get(i).getNomevento();
                        String description = rs1.get(i).getDescripcion();
                        String date = String.valueOf(rs1.get(i).getFecha());
                        String hora = String.valueOf(rs1.get(i).getHora());
                        dm.addRow(new Object[]{id, title, description, date, hora});
                    }
                    break;
                case "educacion":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    // dm.fireTableDataChanged();
                    List<Evento> rs2 = model.FiltrarEventos("Educacion");
                    for (int i = 0; i < rs2.size(); i++) {
                        String id = String.valueOf(rs2.get(i).getEventid());
                        String title = rs2.get(i).getNomevento();
                        String description = rs2.get(i).getDescripcion();
                        String date = String.valueOf(rs2.get(i).getFecha());
                        String hora = String.valueOf(rs2.get(i).getHora());
                        dm.addRow(new Object[]{id, title, description, date, hora});
                    }
                    break;
                case "cultura":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    //dm.fireTableDataChanged();
                    List<Evento> rs3 = model.FiltrarEventos("Cultura");
                    for (int i = 0; i < rs3.size(); i++) {
                        String id = String.valueOf(rs3.get(i).getEventid());
                        String title = rs3.get(i).getNomevento();
                        String description = rs3.get(i).getDescripcion();
                        String date = String.valueOf(rs3.get(i).getFecha());
                        String hora = String.valueOf(rs3.get(i).getHora());
                        dm.addRow(new Object[]{id, title, description, date, hora});
                    }
                    break;
                case "gastronomia":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    //  dm.fireTableDataChanged();
                    List<Evento> rs4 = model.FiltrarEventos("Gastronomia");
                    for (int i = 0; i < rs4.size(); i++) {
                        String id = String.valueOf(rs4.get(i).getEventid());
                        String title = rs4.get(i).getNomevento();
                        String description = rs4.get(i).getDescripcion();
                        String date = String.valueOf(rs4.get(i).getFecha());
                        String hora = String.valueOf(rs4.get(i).getHora());
                        dm.addRow(new Object[]{id, title, description, date, hora});
                    }
                    break;
                case "musica":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    //dm.fireTableDataChanged();  
                    List<Evento> rs5 = model.FiltrarEventos("Música");
                    for (int i = 0; i < rs5.size(); i++) {
                        String id = String.valueOf(rs5.get(i).getEventid());
                        String title = rs5.get(i).getNomevento();
                        String description = rs5.get(i).getDescripcion();
                        String date = String.valueOf(rs5.get(i).getFecha());
                        String hora = String.valueOf(rs5.get(i).getHora());
                        dm.addRow(new Object[]{id, title, description, date, hora});
                    }
                    break;
                case "social":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    //   dm.fireTableDataChanged();
                    List<Evento> rs6 = model.FiltrarEventos("Social");
                    for (int i = 0; i < rs6.size(); i++) {
                        String id = String.valueOf(rs6.get(i).getEventid());
                        String title = rs6.get(i).getNomevento();
                        String description = rs6.get(i).getDescripcion();
                        String date = String.valueOf(rs6.get(i).getFecha());
                        String hora = String.valueOf(rs6.get(i).getHora());
                        dm.addRow(new Object[]{id, title, description, date, hora});
                    }
                    break;
                case "cine":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    //    dm.fireTableDataChanged();

                    List<Evento> rs7 = model.FiltrarEventos("Cine");
                    for (int i = 0; i < rs7.size(); i++) {
                        String id = String.valueOf(rs7.get(i).getEventid());
                        String title = rs7.get(i).getNomevento();
                        String description = rs7.get(i).getDescripcion();
                        String date = String.valueOf(rs7.get(i).getFecha());
                        String hora = String.valueOf(rs7.get(i).getHora());
                        dm.addRow(new Object[]{id, title, description, date, hora});
                    }
                    break;
                case "taller":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    //  dm.fireTableDataChanged();
                    List<Evento> rs8 = model.FiltrarEventos("Taller");
                    for (int i = 0; i < rs8.size(); i++) {
                        String id = String.valueOf(rs8.get(i).getEventid());
                        String title = rs8.get(i).getNomevento();
                        String description = rs8.get(i).getDescripcion();
                        String date = String.valueOf(rs8.get(i).getFecha());
                        String hora = String.valueOf(rs8.get(i).getHora());
                        dm.addRow(new Object[]{id, title, description, date, hora});
                    }
                    break;
                case "infantil":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    //   dm.fireTableDataChanged();
                    List<Evento> rs9 = model.FiltrarEventos("Infantil");
                    for (int i = 0; i < rs9.size(); i++) {
                        String id = String.valueOf(rs9.get(i).getEventid());
                        String title = rs9.get(i).getNomevento();
                        String description = rs9.get(i).getDescripcion();
                        String date = String.valueOf(rs9.get(i).getFecha());
                        String hora = String.valueOf(rs9.get(i).getHora());
                        dm.addRow(new Object[]{id, title, description, date, hora});
                    }
                    break;
                case "deportes":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    //dm.fireTableDataChanged();
                    List<Evento> rsa1 = model.FiltrarEventos("Deportes");
                    for (int i = 0; i < rsa1.size(); i++) {
                        String id = String.valueOf(rsa1.get(i).getEventid());
                        String title = rsa1.get(i).getNomevento();
                        String description = rsa1.get(i).getDescripcion();
                        String date = String.valueOf(rsa1.get(i).getFecha());
                        String hora = String.valueOf(rsa1.get(i).getHora());
                        dm.addRow(new Object[]{id, title, description, date, hora});
                    }
                    break;
                case "religion":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    //     dm.fireTableDataChanged();
                    List<Evento> rsa2 = model.FiltrarEventos("Religion");
                    for (int i = 0; i < rsa2.size(); i++) {
                        String id = String.valueOf(rsa2.get(i).getEventid());
                        String title = rsa2.get(i).getNomevento();
                        String description = rsa2.get(i).getDescripcion();
                        String date = String.valueOf(rsa2.get(i).getFecha());
                        String hora = String.valueOf(rsa2.get(i).getHora());
                        dm.addRow(new Object[]{id, title, description, date, hora});
                    }
                    break;
                /////TERMINA CATEGORÍAS
                ///INICIA MOVIMIENTO DE PANELES
                case "btnVerE":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    //  dm.fireTableDataChanged();
                    // SerRS rsb1 = model.ObtenerEventos();
                    List<Evento> rsb1 = model.ObtenerEventos();
                    // System.out.println(u.getNombre());
                    for (int i = 0; i < rsb1.size(); i++) {
                        String id = String.valueOf(rsb1.get(i).getEventid());
                        String title = rsb1.get(i).getNomevento();
                        String description = rsb1.get(i).getDescripcion();
                        String date = String.valueOf(rsb1.get(i).getFecha());
                        String hora = String.valueOf(rsb1.get(i).getHora());
                        dm.addRow(new Object[]{id, title, description, date, hora});
                    }
                    break;
                case "btnCat":
                    Ocultar("categorias");
                    break;
                case "btnMis":
                    Ocultar("eventos");
                    dm.getDataVector().removeAllElements();
                    // dm.fireTableDataChanged();
                    System.out.println();
                    //SerRS rsc1 = model.MisEventos(u.getNombre());
                    System.out.println(usuarioactual);
                    List<Evento> rsc1 = model.MisEventos(usuarioactual);
                    System.out.println(u.getNombre());
                    for (int i = 0; i < rsc1.size(); i++) {
                        String id = String.valueOf(rsc1.get(i).getEventid());
                        String title = rsc1.get(i).getNomevento();
                        String description = rsc1.get(i).getDescripcion();
                        String date = String.valueOf(rsc1.get(i).getFecha());
                        String hora = String.valueOf(rsc1.get(i).getHora());
                        dm.addRow(new Object[]{id, title, description, date, hora});
                    }
                    break;

                case "btnSig":
                    Ocultar("eventos");

                    dm.getDataVector().removeAllElements();
                    // dm.fireTableDataChanged();
                    System.out.println();
                    //SerRS rsc1 = model.MisEventos(u.getNombre());
                    System.out.println(usuarioactual);
                    List<Evento> rscp = model.Siguiendo(usuarioactual);
                    // System.out.println(u.getNombre());
                    for (int i = 0; i < rscp.size(); i++) {
                        String id = String.valueOf(rscp.get(i).getEventid());
                        String title = rscp.get(i).getNomevento();
                        String description = rscp.get(i).getDescripcion();
                        String date = String.valueOf(rscp.get(i).getFecha());
                        String hora = String.valueOf(rscp.get(i).getHora());
                        dm.addRow(new Object[]{id, title, description, date, hora});
                    }

                    break;

                ///PANEL CREAR EVENTOS
                case "btnCrear":
                    Ocultar("crearE");

                    break;
                ///CREAR EVENTO
                case "btnCE":
                    String nombre = men.crNom.getText();
                    String descrp = men.crDes.getText();
                    String dir = men.crDir.getText();
                    String categoria = men.comboCat.getSelectedItem().toString();
                    //COMPROBACIONES 
                    if (nombre.equals("") || descrp.equals("") || dir.equals("")) {
                        JOptionPane.showMessageDialog(men, "Rellenar todos los campos,por favor.");
                    } else {
                        if (categoria.equals("...")) {
                            JOptionPane.showMessageDialog(men, "Seleccione una categoría,por favor.");
                        } else {
                            LocalDate localdate = men.dateTimePicker.datePicker.getDate();
                            LocalDate today = LocalDate.now();
                            if (!today.isAfter(localdate)) {

                                Date date = Date.valueOf(localdate);
                                LocalTime localtime = men.dateTimePicker.timePicker.getTime();
                                Time time = Time.valueOf(localtime);

                                System.out.println(date);
                                System.out.println(time);
                                System.out.println(u.getUserid());
                                //  Time time = men.spinTime.get
                                boolean exitoE = model.RegistrarEvento(u.getUserid(), nombre, descrp, categoria, dir, date, time);
                                if (exitoE == true) {
                                    men.crNom.setText("");
                                    men.crDir.setText("");
                                    men.crDes.setText("");
                                    men.dateTimePicker.datePicker.setText("");
                                    men.dateTimePicker.timePicker.setText("");
                                    men.comboCat.setSelectedIndex(0);

                                    JOptionPane.showMessageDialog(men, "Evento realizado con éxito.");
                                } else {
                                    JOptionPane.showMessageDialog(men, "Fallo al crear evento.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(men, "La fecha no puede ser anterior a la actual.");
                            }
                            //  ZoneId defaultZoneId = ZoneId.systemDefault();

                        }
                    }

                    break;
                //VOLVER A LOGIN
                case "btnCom2":
                    System.out.println(ev.getEventid());
                    System.out.println(usuarioactual);
                    model.Interes(ev.getEventid(), usuarioactual);

                    JOptionPane.showMessageDialog(men, "Siguiendo evento.");
                    break;
                case "btnCerrarS":
                    men.dispose();
                    log.setVisible(true);
                    break;
                //COMENTARIOS
                case "btnCom":
                    Ocultar("comentarios");
                    men.areaCom.setText("");
                    ArrayList comentarios = model.ObtenerComentarios(ev.getEventid());
                    if (comentarios == null) {
                        System.out.println("Equis no pasa nada");
                    } else {
                        for (int i = 0; i < comentarios.size(); i++) {
                            men.areaCom.append(u.getUserid() + ":" + comentarios.get(i).toString() + "\n");
                        }
                    }

                    break;
                case "btnRep":
                    Ocultar("reportes");
                    men.areaRep.setText("");
                    ArrayList reportes = model.ObtenerReporte(ev.getEventid());
                    if (reportes == null) {
                        System.out.println("Aquí no pasa nada:reporte");
                    } else {
                        for (int i = 0; i < reportes.size(); i++) {
                            men.areaRep.append(u.getUserid() + ":" + reportes.get(i).toString() + "\n");
                        }
                    }

                    break;
                case "btnEnviarCo":

                    men.areaCom.setText("");
                    String mensaje = men.mensaje.getText();
                    model.RegistrarComentarios(ev.getEventid(), u.getUserid(), mensaje);
                    ArrayList comentarios2 = model.ObtenerComentarios(ev.getEventid());
                    for (int i = 0; i < comentarios2.size(); i++) {
                        men.areaCom.append(u.getUserid() + ":" + comentarios2.get(i).toString() + "\n");
                    }
                    break;

                case "btnEnviarRep":

                    men.areaRep.setText("");
                    String causa = men.textCausa.getText();
                    String descri = men.textDesc.getText();
                    model.RegistrarReporte(ev.getEventid(), causa, descri);
                    ArrayList reportes2 = model.ObtenerReporte(ev.getEventid());
                    for (int i = 0; i < reportes2.size(); i++) {
                        men.areaRep.append(u.getUserid() + ":" + reportes2.get(i).toString() + "\n");
                    }
                    break;
                case "Asistire":
                    int event = model.Participantes(ev.getEventid());
                    model.Participacion(ev.getEventid(), u.getUserid());
                    men.part1.setText("");
                    int part = model.Participantes(event);
                    men.part1.setText(part + "");
                    JOptionPane.showMessageDialog(men, "Asistencia confirmada.");

                    break;

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
