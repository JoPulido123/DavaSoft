/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaeventos;

import Controlador.Controlador;
import Vistas.FormLogin;
import Vistas.FormMenuPrincipal;
import Vistas.Registrar;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jopul
 */
public class PracticaEventos {

    
    public static void main(String[] args) {
       try {
           //Crea conexión rmi
            Registry reg=LocateRegistry.getRegistry("localhost",1099);
            proxyInterface mod=  (proxyInterface) reg.lookup("server");
            ///No pasa de aquí
            System.out.println("Hola llegué aquí");
            //Vista vis = new Vista();
            FormLogin log = new FormLogin();
            FormMenuPrincipal men = new FormMenuPrincipal();
            Registrar regi = new Registrar();
            //controlador ctrl = new controlador(vis,mod);
            Controlador ctr = new Controlador(log,regi,men, mod);
            //ctrl.iniciar();
           
            //vis.setVisible(true);
            log.setVisible(true);
        } catch (RemoteException ex) {
            Logger.getLogger(PracticaEventos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PracticaEventos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(PracticaEventos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PracticaEventos.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
