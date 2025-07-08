/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import excepciones.ManoException;

/**
 *
 * @author Agus
 */
public class EstadoManoTerminada extends EstadoMano{
    
    public EstadoManoTerminada(Mano mano) {
        super(mano, "ManoTerminada", Mano.Estados.Terminada);
    }
    
    //POSIBLES ESTADOS

    @Override
    public void esperandoApuesta() throws ManoException {
        throw new ManoException("Ya terminó la mano");
    }

    @Override
    public void apuestaIniciada() throws ManoException {
        throw new ManoException("Ya terminó la mano");
    }

    @Override
    public void pidiendoCartas() throws ManoException {
        throw new ManoException("Ya terminó la mano");
    }

    @Override
    public void terminar() throws ManoException {
        throw new ManoException("Ya terminó la mano");
    }
    
    //METODOS SEGUN ESTADOS
    
    @Override
    public void iniciarApuesta() throws ManoException {
        throw new ManoException("No se puede iniciar una apuesta, la mano terminó");
    }
    
    @Override
    public void pedirCartasParaJugador() throws ManoException {
        throw new ManoException("No se pueden pedir cartas aún, la mano finalizó");
    }

    @Override
    public void agregaarAlistaPaso(Jugador j) throws ManoException {
        throw new ManoException("La mano ya fue terminada");
    }

    @Override
    public void crearApuesta(Mano aThis) throws ManoException {
        throw new ManoException("La mano ya fue terminada");
    }

}
