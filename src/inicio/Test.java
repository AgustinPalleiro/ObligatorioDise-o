/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicio;
import excepciones.CartaException;
import excepciones.MesaException;
import iuGrafica.MenuLogin;
/**
 *
 * @author emili
 */
public class Test {
    
    public static void main(String[] args) throws MesaException, CartaException {
        
        
        // TODO code application logic here
        DatosPrueba.cargar();
        //new Login(null,false).setVisible(true);
        new MenuLogin().setVisible(true);
        
        
    }
    
  
}
