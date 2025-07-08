/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicio;

import excepciones.CartaException;
import excepciones.MesaException;
import logica.Fachada;
import logica.Jugador;

/**
 *
 * @author emili
 */
public class DatosPrueba {
    
    public static void cargar() throws MesaException, CartaException{
    Fachada fachada = Fachada.getInstancia();
    
    
   fachada.agregarJugador(0, "J0", "0", "0");
   fachada.agregarJugador(1000, "J1", "1", "1");
   fachada.agregarJugador(2000, "J2", "2", "2");
   fachada.agregarJugador(3000, "J3", "3", "3");
   fachada.agregarJugador(4000, "J4", "4", "4");
   fachada.agregarJugador(5000, "J5", "5", "5");
   fachada.agregarJugador(6000, "J6", "6", "6");
   fachada.agregarJugador(7000, "J7", "7", "7");
   fachada.agregarJugador(8000, "J8", "8", "8");
   fachada.agregarJugador(9000, "J9", "9", "9");
    
   fachada.agregarAdministrador("A 100", "100", "100");
   fachada.agregarAdministrador("A 200", "101", "200");
   
   fachada.crearMesa(2, 5, 5);
   fachada.crearMesa(3, 5, 100);
    
   
   /*for(Jugador j:fachada.getJugadores()){
  
   System.out.println(j.getNombreCompleto());
  
   }*/
    
    
   }
}
