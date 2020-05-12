/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaeventos;

import java.io.FileInputStream;
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
    public List<Evento>EventoAvanzado(String palabra) throws SQLException,RemoteException;
    public List<Evento> EventoXPrecio(int precio) throws SQLException, RemoteException;
    public int Coordenadas (double lat,double longitud) throws SQLException,RemoteException;
    public List<Evento> EventoXFecha(Date fecha) throws SQLException, RemoteException;
     public List<Usuario> UsuariosSiguiendo(int eventid) throws SQLException,RemoteException;
    public boolean RegistrarEvento(String userid, String nombre, String desc, String categoria, String direccion, Date fecha, Time hora, byte[] bits, int precio,double lat,double log) throws SQLException, RemoteException;

    public boolean ModificarEvento(int eventid, String name, String desc, String address, Date date, Time time,String cateogry,byte[] bits,int precio,double lat,double log) throws SQLException, RemoteException;
    public List<Evento> ObtenerEventos() throws SQLException;

    public boolean RegistrarComentarios(int eventid, String userid, String comentario) throws SQLException;

    public ArrayList ObtenerComentarios(int eventid) throws SQLException;

    public boolean RegistrarReporte(int eventid, String userid, String detalles) throws SQLException;

    public ArrayList ObtenerReporte(int eventid) throws SQLException;

    public boolean RegistrarUsuario(String userid, String pass, String nombre, String direc, String telef) throws SQLException;

    public boolean VerificarUsuario(String userid, String pass) throws SQLException, RemoteException;

    public List<Evento> FiltrarEventos(String categoria) throws SQLException, RemoteException;

    public List<Evento> MisEventos(String userid) throws SQLException;

    public int Participantes(int eventid) throws SQLException, RemoteException;

    public void Participacion(int eventid, String userid) throws SQLException, RemoteException;

    public Evento Informacion(int eventid);

    public boolean BorrarEvento(int eventid);

    public void Interes(int eventid, String userid) throws SQLException, RemoteException;

    public List<Evento> Siguiendo(String userid) throws SQLException, RemoteException;

    public void InsertarPref(String userid, ArrayList<String> categorias) throws SQLException, RemoteException;

    public List<Evento> MisCategorias(String userid) throws SQLException, RemoteException;
}
