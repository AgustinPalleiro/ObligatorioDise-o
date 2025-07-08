/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import excepciones.CartaException;
import excepciones.CredencialesException;
import excepciones.MesaException;
import excepciones.SesionException;
import observador.Observable;
import java.util.ArrayList;

/**
 *
 * @author emili
 */
public class Fachada extends Observable{
    
    private SistemaUsuario sUsuarios = new SistemaUsuario();
    private SistemaMesa sMesa = new SistemaMesa();
    
    private static Fachada instancia = new Fachada();
    
    public static Fachada getInstancia() {
        return instancia;
    }
    
    private Fachada() {
    }

    public SistemaMesa getMesa() {
        return sMesa;
    }
    
    public ArrayList<Jugador> getJugadores(){
        return sUsuarios.getJugadores();
    }

    public ArrayList<Mesa> getListaMesas() {
        return sMesa.getMesa();
    }
    
    public ArrayList<Mesa> getListaMesasAbiertas() {
        return sMesa.getListaMesasAbiertas();
    }
    
    public void agregarJugador(float saldo, String nombreCompleto, String password, String cedula) {
        sUsuarios.agregarJugador(saldo, nombreCompleto, password,cedula);
    }

     public void agregarAdministrador(String nombreCompleto, String password, String cedula) {
        sUsuarios.agregarAdministrador(nombreCompleto, password,cedula);
    }
    
    public Sesion loginJugador(String nom, String pwd) throws CredencialesException, SesionException{
        return sUsuarios.loginJugador(nom, pwd);
    }
    
    public Sesion loginAdministrador(String nom, String pwd) throws CredencialesException, SesionException {
        return sUsuarios.loginAdministrador(nom, pwd);
    }

    public double recaudacionTotal() {
        return sMesa.getMontoTotalMesas();
    }
    
    public void crearMesa(int cantJugadores, double apuestaMinima, int comision) throws MesaException, CartaException{
        sMesa.crearMesa(cantJugadores, apuestaMinima, comision);
    }
    
    public void logout(Sesion s) {
        sUsuarios.logout(s);
    }

    /*public void agregarJugadorAMesa(Mesa seleccionada, Jugador jugador) {
        sMesa.agregarJugadorAMesa(seleccionada, jugador);
    }*/
    
    
  
}
