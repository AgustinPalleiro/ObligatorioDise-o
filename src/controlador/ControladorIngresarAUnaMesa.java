/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import excepciones.MesaException;
import java.util.ArrayList;
import logica.Eventos;
import logica.Fachada;
import logica.Jugador;
import logica.Mesa;
import observador.Observable;
import observador.Observador;

/**
 *
 * @author Agus
 */
public class ControladorIngresarAUnaMesa implements Observador{

    private VistaIngresarAUnaMesa vista;
    private Jugador jugador;
    private Mesa mesaSeleccionada;
    
    public ControladorIngresarAUnaMesa(VistaIngresarAUnaMesa vista, Jugador jugador) {
        this.vista = vista;
        this.jugador = jugador;
        jugador.agregarObservador(this);
        Fachada.getInstancia().agregarObservador(this);
        inicializarVista();
    }
    
    public Jugador getJugador(){
        return jugador;
    }
    
    public Mesa getMesa(){
        return mesaSeleccionada;
    }
    
    private void inicializarVista() {
        cargarListaMesas();
    }
    
    private void cargarListaMesas() {
        vista.mostrarMesas(jugador.getNombreCompleto(), jugador.getSaldo(), Fachada.getInstancia().getListaMesasAbiertas());
    }
    
    @Override
    public void actualizar(Object evento, Observable origen) {
        if((evento.equals(Eventos.eventos.cambioListaMesas)) || (evento.equals(Mesa.EventosMesa.cambioEstadoMesa))){
            cargarListaMesas();
        }
    }
    
    public void ingresarMesa(int pos){
        Mesa seleccionada = null;       
        if(pos >= 0)
            seleccionada = Fachada.getInstancia().getListaMesas().get(pos);
        try{
            this.mesaSeleccionada = seleccionada;
            seleccionada.agregarJugadorAMesa(jugador);         
            if (mesaSeleccionada.getCantJugadoresActual().contains(jugador))
                vista.proximoCasoUso(mesaSeleccionada, jugador);           
        }
        catch(MesaException e){
                vista.mostrarError(e.getMessage());
        }
    }
    
    public void salir() throws MesaException{
        jugador.quitarObservador(this);
        mesaSeleccionada.quitarJugador(jugador);
    }
}

