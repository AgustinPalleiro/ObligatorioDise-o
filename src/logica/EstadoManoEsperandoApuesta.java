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
public class EstadoManoEsperandoApuesta extends EstadoMano{
    
    public EstadoManoEsperandoApuesta(Mano mano) {
        super(mano, "EsperandoApuesta", Mano.Estados.EsperandoApuesta);
    }
    
    //POSIBLES ESTADOS

    @Override
    public void esperandoApuesta() throws ManoException {
        throw new ManoException("Se está esperando apuesta");
    }

    @Override
    public void apuestaIniciada() throws ManoException {
        getMano().cambiarEstado(new EstadoManoApuestaIniciada(getMano()));
    }

    @Override
    public void pidiendoCartas() throws ManoException {
        throw new ManoException("No se inició ninguna apuesta");
    }

    @Override
    public void terminar() throws ManoException {
        getMano().cambiarEstado(new EstadoManoTerminada(getMano()));
    }
    
    //METODOS SEGUN ESTADOS

    
    
    @Override
    public void pedirCartasParaJugador() throws ManoException {
        throw new ManoException("No se pueden pedir cartas aún, se tiene que apostar primero");
    }

    @Override
    public void iniciarApuesta() throws ManoException {
        
    }

    @Override
    public void agregaarAlistaPaso(Jugador j) throws ManoException {
        getMano().hacerAgregarAListaPaso(j);
    }

    @Override
    public void crearApuesta(Mano aThis) throws ManoException {
        getMano().crearApuesta();
    }
}
