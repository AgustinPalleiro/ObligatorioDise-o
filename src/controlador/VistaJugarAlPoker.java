/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import logica.Carta;
import logica.Figura;
import logica.Jugador;
import logica.Mano;
import panelCartasPoker.CartaPoker;

/**
 *
 * @author Agus
 */
public interface VistaJugarAlPoker {
    
    public void mostrarError(String message);
    
    public void mostrarTitulo(int numeroMesa, int numeroManoActual, String nombreCompleto, double saldoJugador);
    
    public void mostrarJugadores(ArrayList<Jugador> listaJugadoresEnMesa);
    
    public void mostrarFiguras(ArrayList<Figura> listaFiguras);
    
    public void cargarCartas(ArrayList<CartaPoker> cartas);

    public void mostrarEstadoMano(String descripcion);
    
    public void mostrarFiguraActual(Jugador j);
    
    public void mostrarPozo(double pozo);
    
}

