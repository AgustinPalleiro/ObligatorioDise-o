/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import excepciones.MesaException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Eventos;
import logica.Fachada;
import logica.Jugador;
import logica.Mano;
import logica.Mesa;
import observador.Observable;
import observador.Observador;

/**
 *
 * @author Agus
 */
public class ControladorEsperaLlenadoMesa implements Observador{
    
    private VistaEsperaLlenadoMesa vista;
    private Jugador jugador;
    private Mesa mesa;
    private Mano mano;

    public ControladorEsperaLlenadoMesa(VistaEsperaLlenadoMesa vista, Mesa mesa, Jugador jugador) {
        this.vista = vista;
        this.mesa = mesa;
        this.jugador = jugador;
        mesa.agregarObservador(this);
        jugador.agregarObservador(this);
        Fachada.getInstancia().agregarObservador(this);
        if (mesa.getEstado().getDescripcion().equals("Iniciada")) {
            inicializarVista();
            ingresarAJugar();
            
        } else {
            inicializarVista();
        }
    }
    
    @Override
    public void actualizar(Object evento, Observable origen) {
        if (evento.equals(Eventos.eventos.cambioListaMesas)) {
            mostrarMensaje();
        }
        if (evento.equals(Mesa.EventosMesa.cambioEstadoMesa)) {
            ingresarAJugar();
        }
    }

    public void inicializarVista(){
        mostrarMensaje();
    }
    
    public void mostrarMensaje(){
        vista.mostrarMensaje(jugador.getNombreCompleto(), mesa.getNumeroDeMesa(), 
                "Esperando inicio del juego, hay " + mesa.cantJugadoresEnMesa() + " de " + mesa.getCantMinimaJugadores() + " en la mesa.");
    }
    
    public void ingresarAJugar(){
        vista.proximoCasoUso(jugador, mesa);
        
    }

    public void salirMesa() throws MesaException {
        mesa.quitarJugador(this.jugador);
        mesa.quitarObservador(this);
    }
}
