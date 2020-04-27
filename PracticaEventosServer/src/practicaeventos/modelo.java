/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaeventos;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jopul
 */
public class modelo extends java.rmi.server.UnicastRemoteObject implements proxyInterface {

    private final Database db;
    final String USER_DB = "Pulido";
    final String PASS_DB = "Universo7788";
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date fechaactual = new Date(System.currentTimeMillis());

    protected modelo() throws RemoteException, ClassNotFoundException {
        db = Database.getDatabase(USER_DB, PASS_DB);

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            //Crea conexión rmi
            proxyInterface server = new modelo();

            Registry reg = LocateRegistry.createRegistry(9001);
            reg.rebind("server", server);
            System.out.println("EJEJEJE");
        } catch (RemoteException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Evento> ObtenerEventos() throws SQLException, RemoteException {
        try {

            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            try (PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM evento WHERE date > ?")) {
                ImageIcon foto;
                InputStream is;
                stmt.setDate(1, sqlDate);

                System.out.println(stmt);
                ResultSet rs = stmt.executeQuery();
                List<Evento> aa = new ArrayList<Evento>();
                System.out.println("Completo");

                while (rs.next()) {
                    Evento ev = new Evento();
                    ev.setEventid(rs.getInt(1));
                    ev.setUserid(rs.getString(2));
                    ev.setNomevento(rs.getString(3));
                    ev.setDescripcion(rs.getString(4));
                    ev.setDireccion(rs.getString(5));
                    ev.setFecha(rs.getDate(6));
                    ev.setHora(rs.getTime(7));
                    ev.setCategoria(rs.getString(8));
                    is = rs.getBinaryStream(9);
                    BufferedImage bi = ImageIO.read(is);
                    foto = new ImageIcon(bi);
                    Image img = foto.getImage();
                    Image newimg = img.getScaledInstance(140, 170, java.awt.Image.SCALE_SMOOTH);
                    ImageIcon newicon = new ImageIcon(newimg);
                    ev.setIcon(newicon);

                    aa.add(ev);
                }
                // SerRS result = new SerRS(rs);
                stmt.close();
                rs.close();
                return aa;

            } catch (IOException ex) {
                Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return null;

    }

    @Override
    public boolean RegistrarComentarios(int eventid, String userid, String comentario) throws SQLException, RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("INSERT INTO commentary"
                    + " VALUES (?,?,?)")) {

                stmt.setString(1, userid);
                stmt.setInt(2, eventid);
                stmt.setString(3, comentario);

                System.out.println(stmt);
                stmt.execute();
                System.out.println("Completo");
                stmt.close();

            }

        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    @Override
    public ArrayList ObtenerComentarios(int eventid) throws SQLException, RemoteException {
        ArrayList comentarios = new ArrayList();

        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT content FROM commentary WHERE eventid = ?")) {

                stmt.setInt(1, eventid);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    comentarios.add(rs.getString(1));
                }
                if (comentarios == null) {
                    rs.close();
                    stmt.close();
                    return null;
                }
                rs.close();
                stmt.close();
                return comentarios;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        // return null;
    }

    @Override
    public boolean RegistrarReporte(int eventid, String userid, String detalles) throws SQLException, RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("INSERT INTO report"
                    + " VALUES (?,?,?)")) {

                stmt.setString(1, userid);
                stmt.setInt(2, eventid);
                stmt.setString(3, detalles);

                System.out.println(stmt);
                stmt.execute();
                System.out.println("Completo");
                stmt.close();

            }

        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    @Override
    public ArrayList ObtenerReporte(int eventid) throws SQLException, RemoteException {
        ArrayList reportes = new ArrayList();

        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT description FROM report WHERE eventid = ?")) {

                stmt.setInt(1, eventid);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    reportes.add(rs.getString(1));
                }
                rs.close();
                stmt.close();
                return reportes;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }

    }

    @Override
    public boolean RegistrarUsuario(String userid, String pass, String nombre, String direc, String telef) throws SQLException, RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("INSERT INTO usuario"
                    + " VALUES (?,?,?,?,?)")) {

                stmt.setString(1, userid);
                stmt.setString(2, pass);
                stmt.setString(3, nombre);

                stmt.setString(4, direc);
                stmt.setString(5, telef);

                System.out.println(stmt);
                stmt.execute();
                System.out.println("Completo");
                stmt.close();

            }

        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return true;

    }

    @Override
    public boolean VerificarUsuario(String userid, String pass) throws SQLException, RemoteException {
        try {
            //System.out.println(pass);
            String query = "SELECT * FROM usuario WHERE userid = ? AND password = ?";
            // DefaultTableModel tmd = db.ejecutaSelect(query);
            //  staffid = Integer.parseInt(tmd.getValueAt(0, 0).toString());

            PreparedStatement preparedStatement = db.con.prepareStatement(query);
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, pass);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                // db.con.close();
                return true;

            } else {

                return false;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }

    }

    @Override
    public boolean RegistrarEvento(String userid, String nombre, String desc, String categoria, String direccion, Date fecha, Time hora, byte[] bits, int precio) throws SQLException, RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("INSERT INTO evento"
                    + " VALUES (DEFAULT,?,?,?,?,?,?,?,?,?)")) {

                stmt.setString(1, userid);
                stmt.setString(2, nombre);
                stmt.setString(3, desc);
                stmt.setString(4, direccion);
                stmt.setDate(5, fecha);
                stmt.setTime(6, hora);
                stmt.setString(7, categoria);
                stmt.setBytes(8, bits);
                // stmt.setBinaryStream(8, fis, longitudBytes);
                stmt.setInt(9, precio);

                System.out.println(stmt);
                stmt.execute();
                System.out.println("Completo");
                stmt.close();

            }

        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean ModificarEvento(int eventid, String name, String desc, String address, Date date, Time time) throws SQLException, RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("UPDATE evento SET name = ?,description = ?,address = ?,date = ?,time = ? WHERE eventid = ? ")) {

                //   stmt.setString(1, eventid);
                stmt.setString(1, name);
                stmt.setString(2, desc);
                stmt.setString(3, address);
                stmt.setDate(4, date);
                stmt.setTime(5, time);
                stmt.setInt(6, eventid);
                System.out.println(stmt);
                stmt.executeUpdate();
                System.out.println("Completo");
                stmt.close();

            }

        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }

        return true;
    }

    @Override
    public List<Evento> FiltrarEventos(String categoria) throws SQLException, RemoteException {
        //ESTE TIENE QUE TENER LÍMITE DE TIEMPO
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM evento WHERE category = ?")) {
                ImageIcon foto;
                InputStream is;
                stmt.setString(1, categoria);
                ResultSet rs = stmt.executeQuery();
                List<Evento> aa = new ArrayList<Evento>();
                System.out.println("Completo");

                while (rs.next()) {
                    Evento ev = new Evento();
                    ev.setEventid(rs.getInt(1));
                    ev.setUserid(rs.getString(2));
                    ev.setNomevento(rs.getString(3));
                    ev.setDescripcion(rs.getString(4));
                    ev.setDireccion(rs.getString(5));
                    Date date = rs.getDate(6);
                    ev.setFecha(date);
                    ev.setHora(rs.getTime(7));
                    ev.setCategoria(rs.getString(8));
                    is = rs.getBinaryStream(9);
                    BufferedImage bi = ImageIO.read(is);
                    foto = new ImageIcon(bi);
                    Image img = foto.getImage();
                    Image newimg = img.getScaledInstance(600, 600, java.awt.Image.SCALE_SMOOTH);
                    ImageIcon newicon = new ImageIcon(newimg);
                    ev.setIcon(newicon);
                    ev.setPrecio(rs.getInt(10));
                    if (!date.before(fechaactual)) {
                        aa.add(ev);
                    }
                    System.out.println(aa);
                }
                stmt.close();
                rs.close();
                return aa;
                // SerRS result = new SerRS(rs);

            } catch (IOException ex) {
                Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return null;
    }

    @Override
    public List<Evento> MisEventos(String userid) throws SQLException, RemoteException {

        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM evento WHERE userid = ?")) {
                ImageIcon foto;
                InputStream is;
                stmt.setString(1, userid);
                ResultSet rs = stmt.executeQuery();
                List<Evento> aa = new ArrayList<Evento>();
                System.out.println("Completo");

                while (rs.next()) {
                    Evento ev = new Evento();
                    ev.setEventid(rs.getInt(1));
                    ev.setUserid(rs.getString(2));
                    ev.setNomevento(rs.getString(3));
                    ev.setDescripcion(rs.getString(4));
                    ev.setDireccion(rs.getString(5));
                    ev.setFecha(rs.getDate(6));
                    ev.setHora(rs.getTime(7));
                    ev.setCategoria(rs.getString(8));
                    is = rs.getBinaryStream(9);
                    BufferedImage bi = ImageIO.read(is);
                    foto = new ImageIcon(bi);
                    Image img = foto.getImage();
                    Image newimg = img.getScaledInstance(250, 200, java.awt.Image.SCALE_SMOOTH);
                    ImageIcon newicon = new ImageIcon(newimg);
                    ev.setIcon(newicon);
                    ev.setPrecio(rs.getInt(10));
                    aa.add(ev);
                }
                // SerRS result = new SerRS(rs);
                stmt.close();
                rs.close();
                return aa;
            } catch (IOException ex) {
                Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return null;
    }

    @Override
    public void Participacion(int eventid, String userid) throws SQLException, RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("INSERT INTO participant"
                    + " VALUES (?,?)")) {

                stmt.setString(1, userid);
                stmt.setInt(2, eventid);

                System.out.println(stmt);
                stmt.execute();

                System.out.println("Completo");
                stmt.close();

            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }

    }

    @Override
    public int Participantes(int eventid) throws SQLException, RemoteException {
        try {
            int po = 0;
            try (PreparedStatement stmt = db.con.prepareStatement("SELECT count(userid) FROM participant WHERE eventid = ?")) {

                stmt.setInt(1, eventid);
                // Informacion(eventid);
                System.out.println(stmt);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    po = rs.getInt(1);
                }
                System.out.println("Completo");
                stmt.close();
                rs.close();
                return po;
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return 0;
    }

    @Override
    public Evento Informacion(int eventid) throws SQLException, RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM evento WHERE eventid = ?")) {
                ImageIcon foto;
                InputStream is;
                stmt.setInt(1, eventid);
                ResultSet rs = stmt.executeQuery();

                System.out.println("Completo");
                Evento ev = new Evento();
                while (rs.next()) {

                    ev.setEventid(rs.getInt(1));
                    ev.setUserid(rs.getString(2));
                    ev.setNomevento(rs.getString(3));
                    ev.setDescripcion(rs.getString(4));
                    ev.setDireccion(rs.getString(5));
                    ev.setFecha(rs.getDate(6));
                    ev.setHora(rs.getTime(7));
                    ev.setCategoria(rs.getString(8));
                    is = rs.getBinaryStream(9);
                    BufferedImage bi = ImageIO.read(is);
                    foto = new ImageIcon(bi);
                    Image img = foto.getImage();
                    Image newimg = img.getScaledInstance(600, 170, java.awt.Image.SCALE_SMOOTH);
                    ImageIcon newicon = new ImageIcon(newimg);
                    ev.setIcon(newicon);
                    ev.setPrecio(rs.getInt(10));

                }
                // SerRS result = new SerRS(rs);
                stmt.close();
                rs.close();
                return ev;
            } catch (IOException ex) {
                Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return null;

    }

    @Override
    public boolean BorrarEvento(int eventid) throws SQLException, RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("DELETE FROM evento WHERE eventid = ?")) {

                stmt.setInt(1, eventid);
                //stmt.setInt(2, eventid);

                System.out.println(stmt);
                stmt.execute();

                System.out.println("Completo");
                stmt.close();
                return true;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public void Interes(int eventid, String userid) throws SQLException, RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("INSERT INTO siguiendo"
                    + " VALUES (?,?)")) {

                stmt.setString(1, userid);
                stmt.setInt(2, eventid);

                System.out.println(stmt);
                stmt.execute();

                System.out.println("Completo");
                stmt.close();

            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    @Override
    public List<Evento> Siguiendo(String userid) throws SQLException, RemoteException {

        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT eventid FROM siguiendo WHERE userid = ?")) {

                stmt.setString(1, userid);
                ResultSet rs = stmt.executeQuery();
                ArrayList al = new ArrayList();
                List<Evento> aa = new ArrayList<Evento>();
                System.out.println("Completo");

                while (rs.next()) {
                    int eventoid = rs.getInt(1);
                    al.add(eventoid);
                }
                stmt.close();
                rs.close();
                for (int i = 0; i < al.size(); i++) {
                    Evento ev = Informacion((int) al.get(i));
                    aa.add(ev);
                }
                return aa;
                // SerRS result = new SerRS(rs);

            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return null;
    }

    @Override
    public void InsertarPref(String userid, ArrayList<String> categorias) throws SQLException, RemoteException {

        for (String categoria : categorias) {
            try {

                try (PreparedStatement stmt = db.con.prepareStatement("INSERT INTO cpreferidos VALUES (?,?)")) {
                    stmt.setString(1, userid);
                    stmt.setString(2, categoria);
                    System.out.println(stmt);
                    stmt.execute();

                    System.out.println("Completo");
                    stmt.close();

                }

            } catch (SQLException ex) {
                System.out.println(ex);

            }
        }

    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    @Override
    public List<Evento> MisCategorias(String userid) throws SQLException, RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT category FROM cpreferidos WHERE userid = ?")) {

                stmt.setString(1, userid);
                ResultSet rs = stmt.executeQuery();
                ArrayList<String> categorias = new ArrayList();
                System.out.println("Completo");
                
                while (rs.next()) {
                    String cate = rs.getString(1);
                    categorias.add(cate);
                }
                stmt.close();
                rs.close();
                if (categorias.isEmpty()) {
                    return null;
                } else {
                    String query = "SELECT eventid FROM evento WHERE category in (";
                    for (String categoria : categorias) {
                        query = query + categoria + ",";
                    }
                    String realquery = removeLastChar(query) + ")";
                    ArrayList al = new ArrayList();
                    List<Evento> aa = new ArrayList<Evento>();
                    PreparedStatement sttt = db.con.prepareStatement(realquery);
                    ResultSet rs1 = stmt.executeQuery();
                    while (rs1.next()) {
                        int eventoid = rs1.getInt(1);
                        al.add(eventoid);
                    }
                    sttt.close();
                    rs1.close();
                    for (int i = 0; i < al.size(); i++) {
                        Evento ev = Informacion((int) al.get(i));
                        aa.add(ev);
                    }
                    return aa;
                    // SerRS result = new SerRS(rs);

                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return null;
    }

    @Override
    public List<Evento> EventoXFecha(Date fecha) throws SQLException, RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT eventid FROM evento WHERE date = ?")) {

                stmt.setDate(1, fecha);
                ResultSet rs = stmt.executeQuery();
                ArrayList al = new ArrayList();
                List<Evento> aa = new ArrayList<Evento>();
                System.out.println("Completo");

                while (rs.next()) {
                    int eventoid = rs.getInt(1);
                    al.add(eventoid);
                }
                stmt.close();
                rs.close();
                for (int i = 0; i < al.size(); i++) {
                    Evento ev = Informacion((int) al.get(i));
                    aa.add(ev);
                }
                return aa;
                // SerRS result = new SerRS(rs);

            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return null;
    }

    @Override
    public List<Evento> EventoXPrecio(int precio) throws SQLException, RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT eventid FROM evento WHERE price <= ?")) {

                stmt.setInt(1, precio);
                ResultSet rs = stmt.executeQuery();
                ArrayList al = new ArrayList();
                List<Evento> aa = new ArrayList<Evento>();
                System.out.println("Completo");
      if(rs == null){
          PreparedStatement stmt1 = db.con.prepareStatement("SELECT eventid FROM evento WHERE price>=100");
          stmt1.setInt(1, precio);
          ResultSet rs1 = stmt1.executeQuery();
          stmt.close();
          rs.close();
          
          while(rs1.next()){
               int eventoid = rs1.getInt(1);
                    al.add(eventoid);
          }
          stmt1.close();
          rs1.close();
          for (int i = 0; i < al.size(); i++) {
                    Evento ev = Informacion((int) al.get(i));
                    aa.add(ev);
                }
          return aa;
      }else{
         while (rs.next()) {
                    int eventoid = rs.getInt(1);
                    al.add(eventoid);
                }
                stmt.close();
                rs.close();
                for (int i = 0; i < al.size(); i++) {
                    Evento ev = Informacion((int) al.get(i));
                    aa.add(ev);
                }
                return aa;  
      }
               
                // SerRS result = new SerRS(rs);

            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return null;
    }

    @Override
    public List<Evento> EventoAvanzado(String palabra) throws SQLException, RemoteException {
      
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT eventid FROM evento WHERE position(? in name) > 0 OR position (? in description)>0")) {

                stmt.setString(1, palabra);
                stmt.setString(2, palabra);
                ResultSet rs = stmt.executeQuery();
                ArrayList al = new ArrayList();
                List<Evento> aa = new ArrayList<Evento>();
                System.out.println("Completo");

                while (rs.next()) {
                    int eventoid = rs.getInt(1);
                    al.add(eventoid);
                }
                stmt.close();
                rs.close();
                for (int i = 0; i < al.size(); i++) {
                    Evento ev = Informacion((int) al.get(i));
                    aa.add(ev);
                }
                return aa;
                // SerRS result = new SerRS(rs);

            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return null;
    }

}
