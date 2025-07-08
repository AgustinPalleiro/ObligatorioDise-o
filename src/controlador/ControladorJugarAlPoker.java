/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import excepciones.ManoException;
import excepciones.MesaException;
import excepciones.SaldoException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Carta;
import logica.EstadoMano;
import logica.Eventos;
import logica.Fachada;
import logica.Jugador;
import logica.Mano;
import logica.Mesa;
import observador.Observable;
import observador.Observador;
import panelCartasPoker.CartaPoker;

/**
 *
 * @author Agus
 */
public class ControladorJugarAlPoker implements Observador {
    
    private VistaJugarAlPoker vista;
    private Jugador jugador;
    private Mesa mesa;
    private Mano mano;
    
    public ControladorJugarAlPoker(VistaJugarAlPoker vista, Jugador jugador, Mesa mesa) throws MesaException{
        this.vista = vista;
        this.mesa = mesa;
        this.jugador = jugador;
        this.mano = mesa.getManoActual();
        mano.setManoEnJugadores();      
        mesa.agregarObservador(this);
        jugador.agregarObservador(this);
        mano.agregarObservador(this);        
        inicializarVista();
    }
    
    
    @Override
    public void actualizar(Object evento, Observable origen) {
       if (evento.equals(Mano.Eventos.cambioEstadoMano)) {
           if(mano.getEstado().getDescripcion().equals("ManoTerminada") && !this.mesa.isManoCreada()){ //La segunda vez no cambia a false (queda en tryue)
               try {
                    mano.quitarObservador(this);
                    mano.jugadorBorrarCartas();
                    mesa.crearMano();
                    actualizarTitulo();
                    //mesa.getManoActual = mano - mano.agragarObservador(this);
                    mesa.manoCreadaVerificar();
                    
                } catch (MesaException ex) {
                    vista.mostrarError(ex.getMessage());
               }       
            }
            /*else{
                ;
            }*/
            try {
               this.mano = mesa.getManoActual();
               mano.setManoEnJugadores();
               mano.agregarObservador(this);
               cargarInformacion();
               System.out.println(jugador.getNombreCompleto());
               System.out.println(jugador.getManoActual().toString());
           } catch (MesaException ex) {
               vista.mostrarError(ex.getMessage());
           }
        }
        if (evento.equals(Jugador.Eventos.cambioSaldo)) {
            actualizarTitulo();
        }
    }

    public Jugador getJugador() {
        return jugador;
    }
    
    public void inicializarVista() throws MesaException{
        cargarInformacion();
    }
    
    public void cargarInformacion() throws MesaException{
        vista.mostrarTitulo(mesa.getNumeroDeMesa(), mesa.numeroManoActual(), jugador.getNombreCompleto(), jugador.getSaldo());
        vista.mostrarJugadores(mesa.getCantJugadoresActual());
        vista.mostrarFiguraActual(jugador);
        vista.mostrarFiguras(mano.getFigurasPosibles());
        vista.mostrarPozo(mano.getPozo().getMonto());
        cargarEstadoMano();
        cargarCartas();
        
    }
    
    public void cargarEstadoMano(){
        EstadoMano estado = mesa.getManoActual().getEstado();
        vista.mostrarEstadoMano(estado.getDescripcion());
    }
    
    private void cargarCartas() throws MesaException {
        vista.cargarCartas(jugador.getCartas());
        System.out.println(jugador.getCartas().toString());
    }
    
    public void salirMesa() throws MesaException {
        mesa.quitarObservador(this);
        mesa.quitarJugador(this.jugador);
    }

    public void agregarAListaPaso() throws ManoException {
        mano.agregarAListaPaso(this.jugador);     
    }

    private void actualizarTitulo() {
        vista.mostrarTitulo(mesa.getNumeroDeMesa(), mesa.numeroManoActual(), jugador.getNombreCompleto(), jugador.getSaldo());
        vista.mostrarPozo(mano.getPozo().getMonto());
    }

public void iniciarApuesta(String monto) throws IllegalArgumentException, SaldoException {
    try {
        mano.agregarApuesta(jugador, monto);
    } catch (NumberFormatException e) {
       
        vista.mostrarError(e.getMessage());
    }
}


    public void ingualarApuesta() throws SaldoException {
        
        mano.igualarApuesta(jugador);
       
    }

}
