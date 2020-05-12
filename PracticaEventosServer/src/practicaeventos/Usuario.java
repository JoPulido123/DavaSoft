/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaeventos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jopul
 */
public class Usuario implements Serializable {
    String nombre;
    ArrayList siguiendo = new ArrayList();
    String userid;
    String tel;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }
    String direc;
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList getSiguiendo() {
        return siguiendo;
    }

    public void setSiguiendo(ArrayList seguidos) {
        this.siguiendo = seguidos;
    }
}
