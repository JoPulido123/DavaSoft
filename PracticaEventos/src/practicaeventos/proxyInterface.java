/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaeventos;

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
public interface proxyInterface {
    public boolean RegistrarEvento(String userid,String nombre,String desc,String categoria,String fecha,String hora) throws SQLException,RemoteException;
    public boolean ModificarEvento(String nombre) throws SQLException,RemoteException;
    public ResultSet ObtenerEventos() throws SQLException,RemoteException;
    public boolean RegistrarComentarios(int eventid,String userid,String comentario) throws SQLException,RemoteException;
    public ArrayList ObtenerComentarios() throws SQLException,RemoteException;
    public void RegistrarReporte(int eventid,String userid,String detalles) throws SQLException,RemoteException;
    public ArrayList ObtenerReporte() throws SQLException,RemoteException;
    public boolean RegistrarUsuario(String userid,String pass,String nombre,String direc,String telef) throws SQLException,RemoteException;
    public boolean VerificarUsuario(String userid,String pass) throws SQLException,RemoteException;
    public ResultSet FiltrarEventos(String categoria) throws SQLException,RemoteException;
    public ResultSet MisEventos(String userid) throws SQLException,RemoteException;
    
    
}
