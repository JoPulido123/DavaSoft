/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vistas.Categorias;
import Vistas.FormLogin;
import Vistas.FormMenuPrincipal;
import Vistas.Registrar;
import com.mxrck.autocompleter.AutoCompleter;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.teamdev.jxmaps.swing.MapView;
import com.teamdev.jxmaps.*;
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
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import practicaeventos.Usuario;
import practicaeventos.proxyInterface;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jopul
 */
public class Controlador extends MapView implements ActionListener, Serializable{

    byte[] bytes = null;

    int longitudBytes;
    private static Map map;
    private Evento ev = new Evento();
    private FormMenuPrincipal men;
    private FormLogin log;
    private Registrar reg;
    private Categorias categ;
    private proxyInterface model;
    private Usuario u = new Usuario();
    String usuarioactual = null;
    final JFXPanel fxpanel = new JFXPanel();
    boolean yey = true;
    DefaultTableModel dm = null;
    DefaultTableModel dd = null;
    ArrayList<String> preferidos = new ArrayList<String>();
    DefaultTableModel de = null;
    int precio;
    byte[] bytesArray = null;
    TextAutoCompleter ac;
    //JFrame frame = new JFrame("Mapa");
    
    
    
 //------------------------------------------------------------------------------------
    public Controlador(FormLogin log, Registrar reg, FormMenuPrincipal men, proxyInterface model, Categorias categ) throws Exception,IOException {
        this.men = men;
        this.model = model;
        this.log = log;
        this.reg = reg;
        this.categ = categ;
       

        // DefaultTableModel dm = (DefaultTableModel) men.jEventos.getModel();
        log.txtUser.addActionListener(this);
        log.txtPass.addActionListener(this);
        log.Registrar.addActionListener(this);
        log.Iniciar.addActionListener(this);
        ///
        reg.txtUser.addActionListener(this);
        reg.txtPass.addActionListener(this);
        reg.btnRegistrar.addActionListener(this);
        reg.btnVolver.addActionListener(this);
        reg.jCategore.addActionListener(this);
        //Categorías Preferidas
        categ.jCheckBox1.addActionListener(this);
        categ.jCheckBox2.addActionListener(this);
        categ.jCheckBox3.addActionListener(this);
        categ.jCheckBox4.addActionListener(this);
        categ.jCheckBox5.addActionListener(this);
        categ.jCheckBox6.addActionListener(this);
        categ.jCheckBox7.addActionListener(this);
        categ.jCheckBox8.addActionListener(this);
        categ.jCheckBox9.addActionListener(this);
        categ.jCheckBox10.addActionListener(this);
        categ.jCheckBox13.addActionListener(this);
        categ.jCheckBox14.addActionListener(this);
        categ.jButton1.addActionListener(this);
        categ.jButton2.addActionListener(this);

        men.crNom.addActionListener(this);
        men.crDir.addActionListener(this);
        men.crDes.addActionListener(this);
        men.btnMapa.addActionListener(this);

        //BUSQUEDA
        men.radioTodos.addActionListener(this);
        men.radioCateg.addActionListener(this);
        ac = new TextAutoCompleter(men.tsugeren);
        ac.addItem("Parillada");
        ac.addItem("COVID");
        ac.addItem("Fiesta");
        ac.addItem("Restaurante");
        ac.addItem("Apoyo para ");
        ac.addItem("Quinceañera");
        ac.addItem("Carne Asada");
        ac.addItem("Baile");
        ac.addItem("Draft");
        ac.addItem("Sorteo");
        ac.addItem("Bienvenida");
        ac.addItem("San Angel");
        men.btClave.addActionListener(this);
        men.btCtria.addActionListener(this);
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
        men.buscarFe.addActionListener(this);
        men.buscarPr.addActionListener(this);
        //EVENTO 
        men.Undo.addActionListener(this);
        men.Undo1.addActionListener(this);
        men.btnCom.addActionListener(this);
        men.btnRep.addActionListener(this);
        men.btnCE.addActionListener(this);
        log.btnAnon.addActionListener(this);
        men.InImg.addActionListener(this);
        ListSelectionModel modelo = men.jEventos.getSelectionModel();

      
        //MOSTRAR INFORMACIÓN DE EVENTO
        men.jEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    try {
                        int linea = men.jEventos.getSelectedRow();
                        if (linea == -1) {
                            JOptionPane.showMessageDialog(men, "Seleccione un evento");
                        } else {
                            int event = Integer.parseInt((String) men.jEventos.getValueAt(linea, 0));
                            //Aca llamamos a la ventana que nos traera el los detalles del registro
                            Ocultar("evento");
                            men.nombre1.setText("");
                            men.desc1.setText("");
                            men.fecha1.setText("");
                            men.hora1.setText("");
                            men.ElblImg.setUI(null);
                            men.jLCosto.setText("");
                            //int selectedrow = men.jEventos.getSelectedRow();
                            //int even = Integer.parseInt((String) men.jEventos.getValueAt(selectedrow, 0));
                            int part = model.Participantes(event);
                            System.out.println(part);
                            Evento aa = model.Informacion(event);
                            ev.setEventid(event);
                            String nombre = aa.getNomevento();
                            String descp = aa.getDescripcion();
                            String address = aa.getDireccion();
                            String date = aa.getFecha().toString();
                            String time = aa.getHora().toString();
                            Image Imag = aa.getIcon().getImage().getScaledInstance(men.ElblImg.getWidth(), men.ElblImg.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon icon = new ImageIcon(Imag);

                            int costo = aa.getPrecio();
                            if (costo == 0) {
                                men.jLCosto.setText("Gratuito");
                            } else {
                                men.jLCosto.setText(costo + "");
                            }
                            men.nombre1.setText(nombre);
                            men.desc1.setText(descp);
                            men.part1.setText(part + "");
                            men.fecha1.setText(date);
                            men.hora1.setText(time);
                            men.ElblImg.setIcon(icon);
                            men.ElblImg.updateUI();

                            men.labelDir.setText(address);
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
        //ELIMINAR EVENTO 
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

        //MODIFICAR EVENTO
        men.jMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent ke) {

                System.out.println(ke.getKeyCode());
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    int seguro = JOptionPane.showConfirmDialog(men, "¿Desea modificar este evento?", "Advertencia", JOptionPane.YES_NO_OPTION);
                    if (seguro == JOptionPane.YES_OPTION) {
                        try {
                            int linea = men.jMod.getSelectedRow();
                            int event = Integer.parseInt((String) men.jMod.getValueAt(linea, 0));
                            String nombre = men.jMod.getValueAt(linea, 1).toString();
                            String desc = men.jMod.getValueAt(linea, 2).toString();
                            String address = men.jMod.getValueAt(linea, 3).toString();
                            String date = men.jMod.getValueAt(linea, 4).toString();
                            String tiempo = men.jMod.getValueAt(linea, 5).toString();
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
    //---MAPA-----
    //MARKER
    public Marker generateMarker(LatLng pos) {
        Marker marker;
        //MarkerLabel value = null;
        MarkerOptions value = new MarkerOptions();
        value.setClickable(true);
        //value.setLabelString("hola");
        marker = new Marker(map);
        marker.setPosition(pos);
        //map.setCenter(pos);
        marker.setOptions(value);
        //System.out.println("Graficado marker");
        return marker;
    }
//Mapa
    public void Mapa() {
       JFrame frame = new JFrame("Tiendas Unison");
       class Mapita extends MapView{
        public Mapita(){
            
        
        setOnMapReadyHandler(new MapReadyHandler() {

            @Override
            public void onMapReady(MapStatus status) {
                if(status==MapStatus.MAP_STATUS_OK){
                    map = getMap();
                    MapOptions mapOptions = new MapOptions();
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    mapOptions.setMapTypeControlOptions(controlOptions);
                    map.setOptions(mapOptions);
                    map.setCenter(new LatLng(29.0817, -110.964));
                    map.setZoom(12);
                }
            }
        });
        
        System.out.print("Espere mientras se genera el mapa");
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.print(".");
            }

        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
           }
        
       
       }
        Mapita mapa = new Mapita();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(mapa, BorderLayout.CENTER);
        //initComponents();
        //setLayout(null);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        frame.setSize(xSize, ySize);
        // frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
        
    }
    
    
        
        
     
    @Override
    public void actionPerformed(ActionEvent e) {
        if (yey == true) {

            String[] columns = {"Evento", "Foto", "Titulo", "Fecha", "Hora"};
            Object[][] rows = {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            };
            DefaultTableModel model = new DefaultTableModel(rows, columns) {
                @Override
                public Class<?> getColumnClass(int column) {
                    switch (column) {
                        case 0:
                            return Integer.class;
                        case 1:
                            return ImageIcon.class;
                        case 2:
                            return String.class;
                        case 3:
                            return String.class;
                        case 4:
                            return String.class;
                        default:
                            return Object.class;
                    }
                }
            };
            men.jEventos.setModel(model);
            men.jEventos.getColumnModel().getColumn(0).setPreferredWidth(5);
            men.jEventos.getColumnModel().getColumn(1).setPreferredWidth(250);
            men.jEventos.getColumnModel().getColumn(2).setPreferredWidth(150);
            men.jEventos.getColumnModel().getColumn(3).setPreferredWidth(30);
            men.jEventos.getColumnModel().getColumn(4).setPreferredWidth(30);

            men.jEventos.setRowHeight(150);

            dm = (DefaultTableModel) men.jEventos.getModel();
            dd = (DefaultTableModel) men.jEvD.getModel();
            de = (DefaultTableModel) men.jMod.getModel();
            //  men.jEventos.getColumn("Foto").setCellRenderer(new myTableCellRenderer());

        }

        yey = false;

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
                    Ocultar("categorias");
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
                    if (user.equals("") || password.isEmpty()) {
                        JOptionPane.showMessageDialog(log, "Inserte un nombre de usuario junto con su contraseña");
                    } else {
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
                            Ocultar("categorias");
                            men.setVisible(true);
                        } else if (aprobado == false) {
                            JOptionPane.showMessageDialog(log, "Usuario/Contraseña incorrectos");
                        }
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
                        if (!preferidos.isEmpty()) {
                            model.InsertarPref(nombreE, preferidos);
                        }

                    } else {
                        JOptionPane.showMessageDialog(reg, "Error al registrarse");
                    }
                    break;

                case "btnDelete":
                    Ocultar("borrar");
                    dd.setRowCount(0);
                    //  dd.getDataVector().removeAllElements();
                    // dm.fireTableDataChanged();
                    System.out.println();
                    //SerRS rsc1 = model.MisEventos(u.getNombre());
                    System.out.println(usuarioactual);
                    List<Evento> rscc = model.MisEventos(usuarioactual);
                    // System.out.println(u.getNombre());
                    for (int i = 0; i < rscc.size(); i++) {
                        String id = String.valueOf(rscc.get(i).getEventid());
                        String title = rscc.get(i).getNomevento();
                        String description = rscc.get(i).getDescripcion();
                        String date = String.valueOf(rscc.get(i).getFecha());
                        String hora = String.valueOf(rscc.get(i).getHora());
                        ImageIcon icon = rscc.get(i).getIcon();
                        dd.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                //VISTA PRINCIPAL
                case "btnModi":
                    Ocultar("modificar");
                    de.setRowCount(0);
                    //de.getDataVector().removeAllElements();
                    // dm.fireTableDataChanged();
                    System.out.println();
                    //SerRS rsc1 = model.MisEventos(u.getNombre());
                    System.out.println(usuarioactual);
                    List<Evento> rscm = model.MisEventos(usuarioactual);
                    System.out.println(u.getNombre());
                    for (int k = 0; k < rscm.size(); k++) {
                        String id = String.valueOf(rscm.get(k).getEventid());
                        String title = rscm.get(k).getNomevento();
                        //   String description = rscm.get(k).getDescripcion();
                        String address = rscm.get(k).getDireccion();
                        String date = String.valueOf(rscm.get(k).getFecha());
                        String hora = String.valueOf(rscm.get(k).getHora());
                        ImageIcon icon = rscm.get(k).getIcon();
                        de.addRow(new Object[]{id, title, address, date, hora});
                    }
                    break;
                // CATEGORÍAS
                case "espectaculos":

                    Ocultar("eventos");
                    // DefaultTableModel dm = (DefaultTableModel) men.jEventos.getModel();
                    //dm.getDataVector().removeAllElements();
                    dm.setRowCount(0);
                    System.out.println("Llego al especto");
                    //SerRS rsz = model.FiltrarEventos("Espectaculo");
                    List<Evento> rs = model.FiltrarEventos("Espectaculos");
                    for (int q = 0; q < rs.size(); q++) {
                        String id = String.valueOf(rs.get(q).getEventid());
                        String title = rs.get(q).getNomevento();
                        // String description = rs.get(q).getDescripcion();
                        String date = String.valueOf(rs.get(q).getFecha());
                        String hora = String.valueOf(rs.get(q).getHora());
                        Image Imag = rs.get(q).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);

                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                case "teatro":
                    Ocultar("eventos");
                    dm.setRowCount(0);
                    // dm.getDataVector().removeAllElements();
                    // dm.fireTableDataChanged();
                    List<Evento> rs1 = model.FiltrarEventos("Teatro");
                    for (int w = 0; w < rs1.size(); w++) {
                        String id = String.valueOf(rs1.get(w).getEventid());
                        String title = rs1.get(w).getNomevento();
                        //  String description = rs1.get(w).getDescripcion();
                        String date = String.valueOf(rs1.get(w).getFecha());
                        String hora = String.valueOf(rs1.get(w).getHora());
                        Image Imag = rs1.get(w).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                case "educacion":
                    Ocultar("eventos");
                    dm.setRowCount(0);
                    //dm.getDataVector().removeAllElements();
                    // dm.fireTableDataChanged();
                    List<Evento> rs2 = model.FiltrarEventos("Educación");
                    for (int r = 0; r < rs2.size(); r++) {
                        String id = String.valueOf(rs2.get(r).getEventid());
                        String title = rs2.get(r).getNomevento();
                        String description = rs2.get(r).getDescripcion();
                        String date = String.valueOf(rs2.get(r).getFecha());
                        String hora = String.valueOf(rs2.get(r).getHora());
                        Image Imag = rs2.get(r).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                case "cultura":
                    Ocultar("eventos");
                    dm.setRowCount(0);
                    //  dm.getDataVector().removeAllElements();
                    //dm.fireTableDataChanged();
                    List<Evento> rs3 = model.FiltrarEventos("Cultura");
                    for (int t = 0; t < rs3.size(); t++) {
                        String id = String.valueOf(rs3.get(t).getEventid());
                        String title = rs3.get(t).getNomevento();
                        String description = rs3.get(t).getDescripcion();
                        String date = String.valueOf(rs3.get(t).getFecha());
                        String hora = String.valueOf(rs3.get(t).getHora());
                        Image Imag = rs3.get(t).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                case "gastronomia":
                    Ocultar("eventos");
                    dm.setRowCount(0);
                    //dm.getDataVector().removeAllElements();
                    //  dm.fireTableDataChanged();
                    List<Evento> rs4 = model.FiltrarEventos("Gastronomía");
                    for (int y = 0; y < rs4.size(); y++) {
                        String id = String.valueOf(rs4.get(y).getEventid());
                        String title = rs4.get(y).getNomevento();
                        String description = rs4.get(y).getDescripcion();
                        String date = String.valueOf(rs4.get(y).getFecha());
                        String hora = String.valueOf(rs4.get(y).getHora());
                        Image Imag = rs4.get(y).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                case "musica":
                    Ocultar("eventos");
                    dm.setRowCount(0);
                    //dm.getDataVector().removeAllElements();
                    //dm.fireTableDataChanged();  
                    List<Evento> rs5 = model.FiltrarEventos("Música");
                    for (int u = 0; u < rs5.size(); u++) {
                        String id = String.valueOf(rs5.get(u).getEventid());
                        String title = rs5.get(u).getNomevento();
                        String description = rs5.get(u).getDescripcion();
                        String date = String.valueOf(rs5.get(u).getFecha());
                        String hora = String.valueOf(rs5.get(u).getHora());
                        Image Imag = rs5.get(u).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                case "social":
                    Ocultar("eventos");
                    dm.setRowCount(0);
                    //dm.getDataVector().removeAllElements();
                    //   dm.fireTableDataChanged();
                    List<Evento> rs6 = model.FiltrarEventos("Social");
                    for (int o = 0; o < rs6.size(); o++) {
                        String id = String.valueOf(rs6.get(o).getEventid());
                        String title = rs6.get(o).getNomevento();
                        String description = rs6.get(o).getDescripcion();
                        String date = String.valueOf(rs6.get(o).getFecha());
                        String hora = String.valueOf(rs6.get(o).getHora());
                        Image Imag = rs6.get(o).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                case "cine":
                    Ocultar("eventos");
                    dm.setRowCount(0);
                    //dm.getDataVector().removeAllElements();
                    //    dm.fireTableDataChanged();

                    List<Evento> rs7 = model.FiltrarEventos("Cine");
                    for (int p = 0; p < rs7.size(); p++) {
                        String id = String.valueOf(rs7.get(p).getEventid());
                        String title = rs7.get(p).getNomevento();
                        String description = rs7.get(p).getDescripcion();
                        String date = String.valueOf(rs7.get(p).getFecha());
                        String hora = String.valueOf(rs7.get(p).getHora());
                        Image Imag = rs7.get(p).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                case "taller":
                    Ocultar("eventos");
                    dm.setRowCount(0);
                    //dm.getDataVector().removeAllElements();
                    //  dm.fireTableDataChanged();
                    List<Evento> rs8 = model.FiltrarEventos("Taller");
                    for (int a = 0; a < rs8.size(); a++) {
                        String id = String.valueOf(rs8.get(a).getEventid());
                        String title = rs8.get(a).getNomevento();
                        String description = rs8.get(a).getDescripcion();
                        String date = String.valueOf(rs8.get(a).getFecha());
                        String hora = String.valueOf(rs8.get(a).getHora());
                        Image Imag = rs8.get(a).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                case "infantil":
                    Ocultar("eventos");
                    dm.setRowCount(0);
                    // dm.getDataVector().removeAllElements();
                    //   dm.fireTableDataChanged();
                    List<Evento> rs9 = model.FiltrarEventos("Infantil");
                    for (int s = 0; s < rs9.size(); s++) {
                        String id = String.valueOf(rs9.get(s).getEventid());
                        String title = rs9.get(s).getNomevento();
                        String description = rs9.get(s).getDescripcion();
                        String date = String.valueOf(rs9.get(s).getFecha());
                        String hora = String.valueOf(rs9.get(s).getHora());
                        Image Imag = rs9.get(s).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                case "deportes":
                    Ocultar("eventos");
                    dm.setRowCount(0);
                    // dm.getDataVector().removeAllElements();
                    //dm.fireTableDataChanged();
                    List<Evento> rsa1 = model.FiltrarEventos("Deportes");
                    for (int d = 0; d < rsa1.size(); d++) {
                        String id = String.valueOf(rsa1.get(d).getEventid());
                        String title = rsa1.get(d).getNomevento();
                        String description = rsa1.get(d).getDescripcion();
                        String date = String.valueOf(rsa1.get(d).getFecha());
                        String hora = String.valueOf(rsa1.get(d).getHora());
                        Image Imag = rsa1.get(d).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                case "religion":
                    Ocultar("eventos");
                    dm.setRowCount(0);
                    //dm.getDataVector().removeAllElements();
                    //     dm.fireTableDataChanged();
                    List<Evento> rsa2 = model.FiltrarEventos("Religión");
                    for (int f = 0; f < rsa2.size(); f++) {
                        String id = String.valueOf(rsa2.get(f).getEventid());
                        String title = rsa2.get(f).getNomevento();
                        String description = rsa2.get(f).getDescripcion();
                        String date = String.valueOf(rsa2.get(f).getFecha());
                        String hora = String.valueOf(rsa2.get(f).getHora());
                        Image Imag = rsa2.get(f).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                /////TERMINA CATEGORÍAS
                ///INICIA MOVIMIENTO DE PANELES
                case "btnVerE":
                    Ocultar("eventos");
                    dm.setRowCount(0);
                    // dm.getDataVector().removeAllElements();
                    //  dm.fireTableDataChanged();
                    // SerRS rsb1 = model.ObtenerEventos();
                    List<Evento> rsb1 = model.ObtenerEventos();
                    // System.out.println(u.getNombre());
                    for (int g = 0; g < rsb1.size(); g++) {
                        String id = String.valueOf(rsb1.get(g).getEventid());
                        String title = rsb1.get(g).getNomevento();
                        String description = rsb1.get(g).getDescripcion();
                        String date = String.valueOf(rsb1.get(g).getFecha());
                        String hora = String.valueOf(rsb1.get(g).getHora());
                        Image Imag = rsb1.get(g).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});

                    }
                    break;
                case "btnCat":
                    Ocultar("categorias");
                    break;
                case "btnMis":
                    Ocultar("eventos");
                    dm.setRowCount(0);
                    // dm.getDataVector().removeAllElements();
                    // dm.fireTableDataChanged();
                    System.out.println();
                    //SerRS rsc1 = model.MisEventos(u.getNombre());
                    System.out.println(usuarioactual);
                    List<Evento> rsc1 = model.MisEventos(usuarioactual);
                    System.out.println(u.getNombre());
                    for (int h = 0; h < rsc1.size(); h++) {
                        String id = String.valueOf(rsc1.get(h).getEventid());
                        String title = rsc1.get(h).getNomevento();
                        String description = rsc1.get(h).getDescripcion();
                        String date = String.valueOf(rsc1.get(h).getFecha());
                        String hora = String.valueOf(rsc1.get(h).getHora());
                        Image Imag = rsc1.get(h).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;

                case "btnSig":
                    Ocultar("eventos");
                    dm.setRowCount(0);
                    //dm.getDataVector().removeAllElements();
                    // dm.fireTableDataChanged();
                    System.out.println();
                    //SerRS rsc1 = model.MisEventos(u.getNombre());
                    System.out.println(usuarioactual);
                    List<Evento> rscp = model.Siguiendo(usuarioactual);
                    // System.out.println(u.getNombre());
                    for (int j = 0; j < rscp.size(); j++) {
                        String id = String.valueOf(rscp.get(j).getEventid());
                        String title = rscp.get(j).getNomevento();
                        String description = rscp.get(j).getDescripcion();
                        String date = String.valueOf(rscp.get(j).getFecha());
                        String hora = String.valueOf(rscp.get(j).getHora());
                        Image Imag = rscp.get(j).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
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
                                int costo = men.jComboCosto.getSelectedIndex();
                                if (costo == 0) {
                                    precio = Integer.parseInt(men.jtextCosto.getText());
                                } else {
                                    precio = 0;
                                }
                                System.out.println(date);
                                System.out.println(time);
                                System.out.println(precio);
                                System.out.println(u.getUserid());
                                //  Time time = men.spinTime.get
                                boolean exitoE = model.RegistrarEvento(u.getUserid(), nombre, descrp, categoria, dir, date, time, bytes, precio);
                                if (exitoE == true) {
                                    men.crNom.setText("");
                                    men.crDir.setText("");
                                    men.crDes.setText("");
                                    men.dateTimePicker.datePicker.setText("");
                                    men.dateTimePicker.timePicker.setText("");
                                    men.comboCat.setSelectedIndex(0);
                                    men.jComboCosto.setSelectedIndex(0);
                                    men.jtextCosto.setText("");
                                    men.lblfoto.setUI(null);
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
                            men.areaRep.append("Reporte" + i + 1 + ":" + reportes.get(i).toString() + "\n");
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
                    model.RegistrarReporte(ev.getEventid(), usuarioactual, descri);
                    ArrayList reportes2 = model.ObtenerReporte(ev.getEventid());
                    for (int i = 0; i < reportes2.size(); i++) {
                        men.areaRep.append("Reporte" + i + 1 + ":" + reportes2.get(i).toString() + "\n");
                    }
                    break;
                case "Asistire":
                    // int event = model.Participantes(ev.getEventid());
                    model.Participacion(ev.getEventid(), u.getUserid());
                    men.part1.setText("");
                    int part = model.Participantes(ev.getEventid());
                    men.part1.setText(part + "");
                    JOptionPane.showMessageDialog(men, "Asistencia confirmada.");

                    break;
                //NUEVOS AÑADIDOSV2
                //CATEGORÍAS PREFERIDAS
                case "CategPref":
                    categ.setVisible(true);
                    break;
                case "SaveC":
                    List<JCheckBox> buttons = new ArrayList<>();
                    preferidos.clear();
                    buttons.add(categ.jCheckBox1);
                    buttons.add(categ.jCheckBox2);
                    buttons.add(categ.jCheckBox3);
                    buttons.add(categ.jCheckBox4);
                    buttons.add(categ.jCheckBox5);
                    buttons.add(categ.jCheckBox6);
                    buttons.add(categ.jCheckBox7);
                    buttons.add(categ.jCheckBox8);
                    buttons.add(categ.jCheckBox9);
                    buttons.add(categ.jCheckBox10);
                    buttons.add(categ.jCheckBox13);
                    buttons.add(categ.jCheckBox14);
                    for (JCheckBox box : buttons) {
                        if (box.isSelected()) {
                            System.out.println(box.getText());
                            preferidos.add(box.getText());
                        }
                    }

                    JOptionPane.showMessageDialog(categ, "Categorías guardadas");
                    categ.setVisible(false);
                    break;

                case "ReturnC":
                    categ.setVisible(false);
                    categ.jCheckBox1.setSelected(false);
                    categ.jCheckBox2.setSelected(false);
                    categ.jCheckBox3.setSelected(false);
                    categ.jCheckBox4.setSelected(false);
                    categ.jCheckBox5.setSelected(false);
                    categ.jCheckBox6.setSelected(false);
                    categ.jCheckBox7.setSelected(false);
                    categ.jCheckBox8.setSelected(false);
                    categ.jCheckBox9.setSelected(false);
                    categ.jCheckBox10.setSelected(false);
                    categ.jCheckBox13.setSelected(false);
                    categ.jCheckBox14.setSelected(false);
                    break;

                case "TdoEvent":
                    dm.setRowCount(0);
                    // dm.getDataVector().removeAllElements();
                    //  dm.fireTableDataChanged();
                    // SerRS rsb1 = model.ObtenerEventos();
                    List<Evento> rskk = model.ObtenerEventos();
                    // System.out.println(u.getNombre());
                    for (int qw = 0; qw < rskk.size(); qw++) {
                        String id = String.valueOf(rskk.get(qw).getEventid());
                        String title = rskk.get(qw).getNomevento();
                        String description = rskk.get(qw).getDescripcion();
                        String date = String.valueOf(rskk.get(qw).getFecha());
                        String hora = String.valueOf(rskk.get(qw).getHora());
                        Image Imag = rskk.get(qw).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                //CATEGORÍAS PREFERIDAS
                case "CtePref":
                    dm.setRowCount(0);
                    // dm.getDataVector().removeAllElements();
                    //  dm.fireTableDataChanged();
                    // SerRS rsb1 = model.ObtenerEventos();

                    List<Evento> rskc = model.MisCategorias(usuarioactual);

                    for (int qz = 0; qz < rskc.size(); qz++) {
                        String id = String.valueOf(rskc.get(qz).getEventid());
                        String title = rskc.get(qz).getNomevento();
                        String description = rskc.get(qz).getDescripcion();
                        String date = String.valueOf(rskc.get(qz).getFecha());
                        String hora = String.valueOf(rskc.get(qz).getHora());
                        Image Imag = rskc.get(qz).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }

                    // System.out.println(u.getNombre());
                    break;
                //CARGAR IMAGEN
                case "InImg":

                    men.lblfoto.setIcon(null);
                    JFileChooser j = new JFileChooser();
                    j.setFileSelectionMode(JFileChooser.FILES_ONLY);//solo archivos y no carpetas
                    int estado = j.showOpenDialog(null);
                    if (estado == JFileChooser.APPROVE_OPTION) {
                        try {
                            FileInputStream fis = new FileInputStream(j.getSelectedFile());
                            //necesitamos saber la cantidad de bytes
                            this.longitudBytes = (int) j.getSelectedFile().length();
                            // bytesArray = new byte[(int) j.getSelectedFile().length()]; 
                            //  fis.read(bytesArray);
                            //fis.close();
                            ByteArrayOutputStream output = new ByteArrayOutputStream();
                            byte[] buffer = new byte[8192];

                            for (int length = 0; (length = fis.read(buffer)) > 0;) {
                                output.write(buffer, 0, length);
                            }

                            bytes = output.toByteArray();
                            try {
                                Image icono = ImageIO.read(j.getSelectedFile()).getScaledInstance(men.lblfoto.getWidth(), men.lblfoto.getHeight(), Image.SCALE_DEFAULT);
                                men.lblfoto.setIcon(new ImageIcon(icono));
                                men.lblfoto.updateUI();

                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(men, "imagen: " + ex);
                            }
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                //BUSCAR POR PRECIO
                case "buscarPr":
                    dm.setRowCount(0);
                    // dm.getDataVector().removeAllElements();
                    //  dm.fireTableDataChanged();
                    // SerRS rsb1 = model.ObtenerEventos();

                    if (men.radioGr.isSelected()) {
                        precio = 0;
                    } else if (men.radioPre.isSelected()) {
                       precio = men.jSlider1.getValue();
                    }
                    System.out.println(precio);
                    List<Evento> rsww = model.EventoXPrecio(precio);

                    for (int ln = 0; ln < rsww.size(); ln++) {
                        String id = String.valueOf(rsww.get(ln).getEventid());
                        String title = rsww.get(ln).getNomevento();
                        String description = rsww.get(ln).getDescripcion();
                        String date = String.valueOf(rsww.get(ln).getFecha());
                        String hora = String.valueOf(rsww.get(ln).getHora());
                        Image Imag = rsww.get(ln).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                //BUSCAR POR FECHA
                case "buscarFe":
                    dm.setRowCount(0);

                    java.sql.Date sqldate = new java.sql.Date(men.jDateChooser1.getDate().getTime());

                    List<Evento> rser = model.EventoXFecha(sqldate);

                    for (int re = 0; re < rser.size(); re++) {
                        String id = String.valueOf(rser.get(re).getEventid());
                        String title = rser.get(re).getNomevento();
                        String description = rser.get(re).getDescripcion();
                        String date = String.valueOf(rser.get(re).getFecha());
                        String hora = String.valueOf(rser.get(re).getHora());
                        Image Imag = rser.get(re).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                case "btCtria":
                    dm.setRowCount(0);

                    String ayud = String.valueOf(men.comboCtria.getSelectedItem());
                    List<Evento> rsgg = model.FiltrarEventos(ayud);

                    for (int ki = 0; ki < rsgg.size(); ki++) {
                        String id = String.valueOf(rsgg.get(ki).getEventid());
                        String title = rsgg.get(ki).getNomevento();
                        String description = rsgg.get(ki).getDescripcion();
                        String date = String.valueOf(rsgg.get(ki).getFecha());
                        String hora = String.valueOf(rsgg.get(ki).getHora());
                        Image Imag = rsgg.get(ki).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                case "btClave":
                    dm.setRowCount(0);

                    String sugerencia = men.tsugeren.getText();

                    List<Evento> rsdd = model.EventoAvanzado(sugerencia);

                    for (int zv = 0; zv < rsdd.size(); zv++) {
                        String id = String.valueOf(rsdd.get(zv).getEventid());
                        String title = rsdd.get(zv).getNomevento();
                        String description = rsdd.get(zv).getDescripcion();
                        String date = String.valueOf(rsdd.get(zv).getFecha());
                        String hora = String.valueOf(rsdd.get(zv).getHora());
                        Image Imag = rsdd.get(zv).getIcon().getImage().getScaledInstance(men.jEventos.getColumnModel().getColumn(1).getWidth(), 130, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(Imag);
                        dm.addRow(new Object[]{id, icon, title, date, hora});
                    }
                    break;
                case "btnMapa":
                    Mapa();
                    //Mapa mapa = new Mapa;
                    break;
                    
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

    
    
    //PARA EL ALUMNO
    

