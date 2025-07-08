/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import excepciones.CartaException;
import excepciones.MesaException;
import excepciones.SaldoException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

/**
 *
 * @author Agus
 */
public class EstadoMesaAbierta extends EstadoMesa{
    
    public EstadoMesaAbierta(Mesa mesa) {
        super(mesa, "Abierta",Mesa.Estados.Abierta);
    }

    @Override
    public void abrir() throws MesaException {
        throw new MesaException("La mesa ya está abierta");
    }

    @Override
    public void iniciar() throws MesaException {
        getMesa().cambiarEstado(new EstadoMesaIniciada(getMesa()));
    }

    @Override
    public void finalizar() throws MesaException {
        throw new MesaException("La mesa está abierta aún, no se puede finalizar"); //Preguntar si se puede finalizar sin haber estado iniciada
    }
    
    @Override
    public void agregarJugadorAMesa(Jugador jugador) throws MesaException{
        getMesa().hacerAgregarJugadorAMesa(jugador);
    }

    @Override
    public void quitarJugador(Jugador jugador) {
        getMesa().hacerQuitarJugador(jugador);
    }

    @Override
    public void crearMano(Mesa mesa/*, Pozo pozo*/) throws MesaException {
       
        try {
            getMesa().hacerCrearMano(mesa);
        } catch (CartaException ex) {
            java.util.logging.Logger.getLogger(EstadoMesaAbierta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SaldoException ex) {
            java.util.logging.Logger.getLogger(EstadoMesaAbierta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }
}
