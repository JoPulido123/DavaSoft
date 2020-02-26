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
import java.util.List;

/**
 *
 * @author jopul
 */
public interface proxyInterface {
    public boolean RegistrarEvento(String userid,String nombre,String desc,String categoria,String direccion,Date fecha,Time hora) throws SQLException;
    public boolean ModificarEvento(String nombre) throws SQLException;
    public List<Evento> ObtenerEventos() throws SQLException;
    public boolean RegistrarComentarios(int eventid,String userid,String comentario) throws SQLException;
    public ArrayList ObtenerComentarios(int eventid) throws SQLException;
    public void RegistrarReporte(int eventid,String userid,String detalles) throws SQLException;
    public ArrayList ObtenerReporte(int eventid) throws SQLException;
    public boolean RegistrarUsuario(String userid,String pass,String nombre,String direc,String telef) throws SQLException;
    public boolean VerificarUsuario(String userid,String pass) throws SQLException;
    public List<Evento> FiltrarEventos(String categoria) throws SQLException,RemoteException; 
    public List<Evento> MisEventos(String userid) throws SQLException;
    public int Participantes(int eventid) throws SQLException,RemoteException;
    public void Participacion(int eventid,String userid) throws SQLException,RemoteException;
    public Evento Informacion(int eventid);
}
