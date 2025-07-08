/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iuGrafica;

import vistaEscritorio.IngresarAUnaMesa;
import excepciones.CredencialesException;
import excepciones.SesionException;
import java.awt.Frame;
import logica.Fachada;
import logica.Jugador;
import logica.Sesion;

/**
 *
 * @author Agus
 */
public class LoginJugador extends Login{
    
    public LoginJugador(Frame parent, boolean modal) {
        super(parent, modal, "Ingreso a la aplicación");
    }

    @Override
    public Object llamarLogin(String nombre, String password) throws CredencialesException, SesionException{
        return Fachada.getInstancia().loginJugador(nombre, password);
    }

    @Override
    public void proximoCasoUso(Object sesion) {
        //new Menu.setVisible(true); //Versión sin sesiones
        Sesion s = (Sesion) sesion; // resultado es de tipo Sesion
        Jugador jugador = (Jugador) s.getUsuario();
        new IngresarAUnaMesa(null, false, jugador).setVisible(true); //Crear clase Sesión en carpeta lógica
    }
    
}
