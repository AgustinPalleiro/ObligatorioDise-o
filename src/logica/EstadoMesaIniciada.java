/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import excepciones.CartaException;
import excepciones.MesaException;
import excepciones.SaldoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Agus
 */
public class EstadoMesaIniciada extends EstadoMesa {
    
    public EstadoMesaIniciada(Mesa mesa) {
        super(mesa, "Iniciada",Mesa.Estados.Iniciada);
    }

    @Override
    public void abrir() throws MesaException {
        throw new MesaException("La mesa ya fue iniciada, ya se ingresaron todos los jugadores posibles");
    }

    @Override
    public void iniciar() throws MesaException {
        throw new MesaException("La mesa ya fue iniciada, ya se ingresaron todos los jugadores posibles");
    }

    @Override
    public void finalizar() throws MesaException {
        getMesa().cambiarEstado(new EstadoMesaFinalizada(getMesa()));
    }
    
    @Override
    public void agregarJugadorAMesa(Jugador jugador) throws MesaException{
        throw new MesaException("La mesa ya fue iniciada, ya se ingresaron todos los jugadores posibles");
    }

    @Override
    public void quitarJugador(Jugador jugador) {
        //Controlar estado de mano para que el jugador salga (solo puede salir luego de finalizada una mano)
        getMesa().hacerQuitarJugador(jugador);
    }
    
    @Override
    public void crearMano(Mesa mesa) throws MesaException {
        try {
            getMesa().hacerCrearMano(mesa);
        } catch (CartaException ex) {
            Logger.getLogger(EstadoMesaIniciada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SaldoException ex) {
            Logger.getLogger(EstadoMesaIniciada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
