/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iuGrafica;

import excepciones.CredencialesException;
import excepciones.SesionException;
import java.awt.Frame;
import logica.Administrador;
import logica.Fachada;
import logica.Sesion;
import vistaEscritorio.AdministrarMesa;

/**
 *
 * @author Agus
 */
public class LoginAdministrador extends Login{
    
    public LoginAdministrador(Frame parent, boolean modal) {
        super(parent, modal, "Login ADMINISTRADOR");
    }

    @Override
    public Sesion llamarLogin(String nombre, String password) throws CredencialesException, SesionException {
        return Fachada.getInstancia().loginAdministrador(nombre, password);
    }


    @Override
    public void proximoCasoUso(Object resultado) {
        Sesion sesion = (Sesion) resultado; // resultado es de tipo Sesion
        Administrador admin = (Administrador) sesion.getUsuario(); // Obtener el usuario dentro de la sesión
        new AdministrarMesa(null, false, admin).setVisible(true);
    }

}
