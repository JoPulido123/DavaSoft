/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaeventos;

import Controlador.Controlador;
import Vistas.Categorias;
import Vistas.FormLogin;
import Vistas.FormMenuPrincipal;
import Vistas.Registrar;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;


/**
 *
 * @author jopul
 */
public class PracticaEventos {

    
    public static void main(String[] args) throws RemoteException, NotBoundException, Exception{
       
           //Crea conexión rmi
            Registry reg=LocateRegistry.getRegistry("localhost",9001);
           // reg.rebind("server", reg);
            proxyInterface mod=  (proxyInterface) reg.lookup("server");
            //UnicastRemoteObject.exportObject((Remote) mod, 9001);
            //System.out.println("Hola llegué aquí");
           
            //Vista vis = new Vista();
            Categorias cat = new Categorias();
            FormLogin log = new FormLogin();
            FormMenuPrincipal men = new FormMenuPrincipal();
            Registrar regi = new Registrar();
            //controlador ctrl = new controlador(vis,mod);
           
             Controlador ctr = new Controlador(log,regi,men, mod,cat);
            //ctrl.iniciar();
           
            //vis.setVisible(true);
            log.setVisible(true);
          
        
    }
    
}
