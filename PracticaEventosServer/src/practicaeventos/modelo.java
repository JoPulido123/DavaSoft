/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaeventos;

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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jopul
 */
public class modelo extends UnicastRemoteObject implements proxyInterface {

    private final Database db;
    final String USER_DB = "Pulido";
    final String PASS_DB = "Universo7788";

    protected modelo() throws RemoteException, ClassNotFoundException {
        db = Database.getDatabase(USER_DB, PASS_DB);

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            //Crea conexión rmi
            proxyInterface server = new modelo();
            Registry reg = LocateRegistry.createRegistry(9001);
            reg.rebind("server", server);
        } catch (RemoteException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Evento> ObtenerEventos() throws SQLException, RemoteException {
        try {

            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            try (PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM evento WHERE date > ?")) {

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

                    aa.add(ev);
                }
                // SerRS result = new SerRS(rs);
                stmt.close();
                rs.close();
                return aa;

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
                if(comentarios==null){
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

                stmt.setString(2, userid);
                stmt.setInt(1, eventid);
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
    public boolean RegistrarEvento(String userid, String nombre, String desc, String categoria, String direccion, Date fecha, Time hora) throws SQLException, RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("INSERT INTO evento"
                    + " VALUES (DEFAULT,?,?,?,?,?,?,?)")) {

                stmt.setString(1, userid);
                stmt.setString(2, nombre);
                stmt.setString(3, desc);
                stmt.setString(4, direccion);
                stmt.setDate(5, fecha);
                stmt.setTime(6, hora);
                stmt.setString(7, categoria);

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
    public boolean ModificarEvento(int eventid,String name,String desc,String address,Date date,Time time) throws SQLException, RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("UPDATE evento SET name = ?,description = ?,address = ?,date = ?,time = ? WHERE eventid = ? ")) {

             //   stmt.setString(1, eventid);
                stmt.setString(1, name);
                stmt.setString(2, desc);
                stmt.setString(3, address);
                stmt.setDate(4,date);
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
       
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM evento WHERE category = ?")) {

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
                    ev.setFecha(rs.getDate(6));
                    ev.setHora(rs.getTime(7));
                    ev.setCategoria(rs.getString(8));

                    aa.add(ev);
                    System.out.println(aa);
                }
                stmt.close();
                rs.close();
                return aa;
                // SerRS result = new SerRS(rs);
              
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

                    aa.add(ev);
                }
                // SerRS result = new SerRS(rs);
                stmt.close();
                rs.close();
                return aa;
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
             int po=0;
            try (PreparedStatement stmt = db.con.prepareStatement("SELECT count(userid) FROM participant WHERE eventid = ?")) {

                stmt.setInt(1, eventid);
               // Informacion(eventid);
                System.out.println(stmt);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
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

                    
                }
                // SerRS result = new SerRS(rs);
                stmt.close();
                rs.close();
                return ev;
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

}
