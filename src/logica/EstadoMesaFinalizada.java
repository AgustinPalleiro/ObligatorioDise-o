/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import excepciones.MesaException;

/**
 *
 * @author Agus
 */
public class EstadoMesaFinalizada extends EstadoMesa {
    
    public EstadoMesaFinalizada(Mesa mesa) {
        super(mesa, "Finalizada",Mesa.Estados.Finalizada);
    }

    @Override
    public void abrir() throws MesaException {
        throw new MesaException("No se puede abrir. La mesa ya finalizó");
    }

    @Override
    public void iniciar() throws MesaException {
        throw new MesaException("No se puede iniciar. La mesa ya finalizó");
    }

    @Override
    public void finalizar() throws MesaException {
        throw new MesaException("La mesa ya finalizó");
    }
    
    @Override
    public void agregarJugadorAMesa(Jugador jugador) throws MesaException{
        throw new MesaException("La mesa ya finalizó, no es posible ingresar");
    }

    @Override
    public void quitarJugador(Jugador jugador) throws MesaException{ //Controlar cuando el jugador puede salir y cuando no
        throw new MesaException("Ya ha salido de la mesa");
    }

    @Override
    public void crearMano(Mesa mesa/*, Pozo pozo*/) throws MesaException {
        throw new MesaException("No se puede crear una mano, la mesa no está iniciada");
    }
    
    
}
