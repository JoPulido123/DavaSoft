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
            System.out.println(ex);        }
    }

    @Override
    public JDBCTableAdpater ObtenerEventos() throws SQLException,RemoteException {
        try {
             ResultSet rs = null;
            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            try (PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM evento WHERE date > ?")) {

                stmt.setDate(1, sqlDate);
              
                System.out.println(stmt);
                 rs = stmt.executeQuery();
                
                System.out.println("Completo");
                stmt.close();
                JDBCTableAdpater modelo = new JDBCTableAdpater(rs);
               // SerRS result = new SerRS(rs);
                rs.close();
                return modelo;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        
        }
        return null;
       
      
    }

    @Override
    public boolean RegistrarComentarios(int eventid, String userid, String comentario) throws SQLException,RemoteException {
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
    public ArrayList ObtenerComentarios(String eventid) throws SQLException,RemoteException {
        ArrayList comentarios = new ArrayList();

        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM commentary WHERE eventid = ?")) {

                stmt.setString(1, eventid);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    comentarios.add(rs.getString(1));
                }
                rs.close();
                stmt.close();
               return comentarios;
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return comentarios;
    }

    @Override
    public boolean RegistrarReporte(int eventid, String userid, String detalles) throws SQLException,RemoteException {
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
    public ArrayList ObtenerReporte(String eventid) throws SQLException,RemoteException {
       ArrayList reportes = new ArrayList();

        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM report WHERE eventid = ?")) {

                stmt.setString(1, eventid);
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

        }
        return reportes;
    }

    @Override
    public boolean RegistrarUsuario(String userid, String pass,String nombre, String direc,String telef) throws SQLException,RemoteException {
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
    public boolean VerificarUsuario(String userid, String pass) throws SQLException,RemoteException {
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
                db.con.close();
                return false;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    @Override
    public boolean RegistrarEvento(String userid, String nombre, String desc, String categoria,String direccion, Date fecha, Time hora) throws SQLException,RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("INSERT INTO evento"
                    + " VALUES (DEFAULT,?,?,?,?,?,?,?)")) {

                stmt.setString(1, userid);
                stmt.setString(2, nombre);
                stmt.setString(3, desc);
                stmt.setString(4,direccion );
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
    public boolean ModificarEvento(String eventid) throws SQLException,RemoteException {
          try {

            try (PreparedStatement stmt = db.con.prepareStatement("UPDATE evento SET  ")) {

                stmt.setString(1, eventid);
               

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
    public JDBCTableAdpater FiltrarEventos(String categoria) throws SQLException, RemoteException {
        JDBCTableAdpater modelo = null;
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM evento WHERE category = ?")) {

                stmt.setString(1, categoria);
                ResultSet rs = stmt.executeQuery();
                rs.close();
                stmt.close();
               
                 modelo = new JDBCTableAdpater(rs);
                return modelo;
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
      return modelo;
    }

    @Override
    public JDBCTableAdpater MisEventos(String userid) throws SQLException, RemoteException {
        JDBCTableAdpater modelo = null;
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM evento WHERE userid = ?")) {

                stmt.setString(1, userid);
                ResultSet rs = stmt.executeQuery();
                rs.close();
                stmt.close();
                 modelo = new JDBCTableAdpater(rs);
                return modelo;
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return modelo;
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

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT count(userid) FROM participant WHERE = ?")) {

                
                stmt.setInt(1, eventid);
             

                System.out.println(stmt);
                stmt.execute();
                System.out.println("Completo");
                stmt.close();

            }

        } catch (SQLException ex) {
            System.out.println(ex);
            
        }
       return 0;
    }

}
