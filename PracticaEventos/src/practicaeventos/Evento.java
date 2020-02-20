/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaeventos;

//import java.sql.Date;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author jopul
 */
public class Evento {
  String userid;
  String nomevento;
  String descripcion;
  String direccion;
  Date fecha;
  Time hora;

    public Evento(String userid, String nomevento, String descripcion, String direccion, Date fecha, Time hora) {
        this.userid = userid;
        this.nomevento = nomevento;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNomevento() {
        return nomevento;
    }

    public void setNomevento(String nomevento) {
        this.nomevento = nomevento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
  
  
}
