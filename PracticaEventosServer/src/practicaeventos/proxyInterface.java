/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaeventos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author jopul
 */
public interface proxyInterface extends Remote {
    public boolean RegistrarEvento(String userid,String nombre,String desc,String categoria,String direccion,Date fecha,Time hora) throws SQLException,RemoteException;
    public boolean ModificarEvento(String nombre) throws SQLException,RemoteException;
    public JDBCTableAdpater ObtenerEventos() throws SQLException,RemoteException;
    public boolean RegistrarComentarios(int eventid,String userid,String comentario) throws SQLException,RemoteException;
    public ArrayList ObtenerComentarios(String eventid) throws SQLException,RemoteException;
    public boolean RegistrarReporte(int eventid,String userid,String detalles) throws SQLException,RemoteException;
    public ArrayList ObtenerReporte(String eventid) throws SQLException,RemoteException;
    public boolean RegistrarUsuario(String userid,String pass,String nombre,String direc,String telef) throws SQLException,RemoteException;
    public boolean VerificarUsuario(String userid,String pass) throws SQLException,RemoteException;
    public JDBCTableAdpater FiltrarEventos(String categoria) throws SQLException,RemoteException;
    public JDBCTableAdpater MisEventos(String userid) throws SQLException,RemoteException;
    public void Participacion(int eventid,String userid) throws SQLException,RemoteException;
    public int Participantes(int eventid) throws SQLException,RemoteException;
}
