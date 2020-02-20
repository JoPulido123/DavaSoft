/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaeventos;

import java.io.Serializable;
import java.sql.ResultSet;

/**
 * CLASE PARA PODER MANDAR RESULT SETS AL CLIENTE
 */
public class SerRS implements Serializable{
    public ResultSet rs;
    public SerRS(ResultSet data){
        this.rs=data;
    }
}
