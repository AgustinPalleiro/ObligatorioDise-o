/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import excepciones.CredencialesException;
import excepciones.SesionException;
import java.util.ArrayList;

/**
 *
 * @author emili
 */
public class SistemaUsuario {
    
    
    private ArrayList<Jugador> jugadores = new ArrayList();
    private ArrayList<Administrador> administradores = new ArrayList();
    private ArrayList<Sesion> sesiones = new ArrayList();
    
    
    public void agregarJugador(float saldo, String nombreCompleto, String password, String cedula){
        jugadores.add(new Jugador(saldo, nombreCompleto, password,cedula));
    }
    
    public void agregarAdministrador(String nombreCompleto, String password, String cedula){
        administradores.add(new Administrador(nombreCompleto, password,cedula));
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public ArrayList<Administrador> getAdministradores() {
        return administradores;
    }
    
    public ArrayList<Sesion> getSesiones() {
        return sesiones;
    }
    
    
    public Sesion loginJugador(String nom,String pwd) throws CredencialesException, SesionException{
        Sesion sesion = null;
        Jugador jugador = (Jugador) login(nom, pwd, jugadores);

        if (jugador == null) {
            throw new CredencialesException("Credenciales incorrectas.");
        }

        for (Sesion s : sesiones) {
            if (s.getUsuario().equals(jugador)) 
                throw new CredencialesException("Acceso denegado. El usuario ya está logueado.");
        }
        sesion = new Sesion(jugador);
        sesiones.add(sesion);
        return sesion;
    }
    
    public Sesion loginAdministrador(String nom, String pwd) throws CredencialesException, SesionException{
        Sesion sesion = null;
        Administrador admin = (Administrador) login(nom, pwd, administradores);

        if (admin == null) {
            throw new CredencialesException("Credenciales incorrectas.");
        }
        
        for (Sesion s : sesiones) {
            if (s.getUsuario().equals(admin)) 
                throw new CredencialesException("Acceso denegado. El usuario ya está logueado.");
        }
        sesion = new Sesion(admin);
        sesiones.add(sesion);
        return sesion;
    }

    
    private Usuario login(String nom, String pwd, ArrayList lista){
        Usuario usuario;
        for(Object o: lista){
            usuario = (Usuario)o;
            if(usuario.getNombreCompleto().equals(nom) && usuario.getPassword().equals(pwd)){
                return usuario;
            }
        }
        return null;
    }
    
    public void logout(Sesion sesion){
        sesiones.remove(sesion);
        //Fachada.getInstancia().avisar(EventosGenerales.eventos.cambioListaSesiones); //Ver como utilizar los eventos para avisar el logout
    }
      
}
