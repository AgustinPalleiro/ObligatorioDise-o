/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import excepciones.ManoException;
import excepciones.SaldoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Agus
 */
public class EstadoManoPidiendoCartas extends EstadoMano{
    
    public EstadoManoPidiendoCartas(Mano mano) {
        super(mano, "PidindoCartas", Mano.Estados.PidiendoCartas);
    }
    
    //POSIBLES ESTADOS

    @Override
    public void esperandoApuesta() throws ManoException {
        throw new ManoException("Se están pidiendo cartas");
    }

    @Override
    public void apuestaIniciada() throws ManoException {
        throw new ManoException("No se puede iniciar una nueva apuesta, se están pidiendo cartas");
    }

    @Override
    public void pidiendoCartas() throws ManoException {
        throw new ManoException("Se están pidiendo cartas");
    }

    @Override
    public void terminar() throws ManoException {
        getMano().cambiarEstado(new EstadoManoTerminada(getMano()));
    }
    
    //METODOS SEGUN ESTADOS
    
    @Override
    public void iniciarApuesta() throws ManoException {
        throw new ManoException("No se puede iniciar una apuesta, se están pidiendo cartas");
    }
    
    @Override
    public void pedirCartasParaJugador() throws ManoException {
        try {
            getMano().asignarCartas();
        } catch (SaldoException ex) {
            Logger.getLogger(EstadoManoPidiendoCartas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void agregaarAlistaPaso(Jugador j) throws ManoException {
         throw new ManoException("No se puede psar mientras se esta pidiendo cartas");
    }

    @Override
    public void crearApuesta(Mano aThis) throws ManoException {
        throw new ManoException("Ya hay una apuesta en curso!!");
    }

    
}
