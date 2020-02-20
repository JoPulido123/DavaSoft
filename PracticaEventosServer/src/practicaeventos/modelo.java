/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaeventos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
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

    protected modelo() throws RemoteException {
        db = Database.getDatabase(USER_DB, PASS_DB);

    }

    public static void main(String[] args) throws SQLException {
        try {
            //Crea conexión rmi
            proxyInterface server = new modelo();
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("server", server);
        } catch (RemoteException ex) {
            Logger.getLogger(modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ResultSet ObtenerEventos() throws RemoteException, SQLException {
        Statement stmt = db.con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Evento");
        return rs;
    }

    @Override
    public boolean RegistrarComentarios(int eventid, String userid, String comentario) throws RemoteException, SQLException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("INSERT INTO Comentario"
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
    public ArrayList ObtenerComentarios(String eventid) throws RemoteException, SQLException {
        ArrayList comentarios = new ArrayList();

        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM Commentary WHERE EventID = ?")) {

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
    public boolean RegistrarReporte(int eventid, String userid, String detalles) throws RemoteException, SQLException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("INSERT INTO Reporte"
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
    public ArrayList ObtenerReporte(String eventid) throws RemoteException, SQLException {
       ArrayList reportes = new ArrayList();

        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM Report WHERE EventID = ?")) {

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
    public boolean RegistrarUsuario(String userid, String pass,String nombre, String telef, String direc) throws RemoteException, SQLException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("INSERT INTO Usuario"
                    + " VALUES (?,?,?,?,?,?)")) {

                stmt.setString(1, userid);
                stmt.setString(2, pass);
                stmt.setString(3, telef);
                stmt.setString(4, direc);

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
            String query = "SELECT * FROM Usuario WHERE UserID = ? AND Password = ?";
            // DefaultTableModel tmd = db.ejecutaSelect(query);
            //  staffid = Integer.parseInt(tmd.getValueAt(0, 0).toString());

            PreparedStatement preparedStatement = db.con.prepareStatement(query);
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, pass);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                db.con.close();
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
    public boolean RegistrarEvento(String userid, String nombre, String desc, String categoria, Date fecha, Time hora) throws SQLException, RemoteException {
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("INSERT INTO Evento"
                    + " VALUES (?,?,?,?,?,?)")) {

                stmt.setString(1, userid);
                stmt.setString(2, nombre);
                stmt.setString(3, desc);
                stmt.setString(4, categoria);
                stmt.setDate(5, fecha);
                stmt.setTime(6, hora);

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
    public boolean ModificarEvento(String eventid) throws SQLException, RemoteException {
          try {

            try (PreparedStatement stmt = db.con.prepareStatement("UPDATE ")) {

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
    public ResultSet FiltrarEventos(String categoria) throws SQLException, RemoteException {
        ResultSet s = null;
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM Evento WHERE category = ?")) {

                stmt.setString(1, categoria);
                ResultSet rs = stmt.executeQuery();
                rs.close();
                stmt.close();
                return rs;
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return s;
    }

    @Override
    public ResultSet MisEventos(String userid) throws SQLException, RemoteException {
        ResultSet s = null;
        try {

            try (PreparedStatement stmt = db.con.prepareStatement("SELECT * FROM Evento WHERE UserID = ?")) {

                stmt.setString(1, userid);
                ResultSet rs = stmt.executeQuery();
                rs.close();
                stmt.close();
                return rs;
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return s;
    }
}
