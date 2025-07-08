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
public class EstadoManoApuestaIniciada extends EstadoMano{
    
    public EstadoManoApuestaIniciada(Mano mano) {
        super(mano, "ApuestaIniciada", Mano.Estados.ApuestaIniciada);
    }
    
    //POSIBLES ESTADOS

    @Override
    public void esperandoApuesta() throws ManoException {
        throw new ManoException("No se puede iniciar apuesta, ya fue iniciada");
    }

    @Override
    public void apuestaIniciada() throws ManoException {
        throw new ManoException("No se puede iniciar apuesta, ya fue iniciada");
    }

    @Override
    public void pidiendoCartas() throws ManoException {
        getMano().cambiarEstado(new EstadoManoPidiendoCartas(getMano()));
    }

    @Override
    public void terminar() throws ManoException {
        getMano().cambiarEstado(new EstadoManoTerminada(getMano()));
    }

    
    //METODOS SEGUN ESTADOS
    
    @Override
    public void pedirCartasParaJugador() throws ManoException {
        throw new ManoException("No se pueden pedir cartas aún");
    }

    @Override
    public void iniciarApuesta() throws ManoException {
        throw new ManoException("No se puede iniciar apuesta, ya alguien apostó");
    }

    @Override
    public void agregaarAlistaPaso(Jugador j) throws ManoException {
         getMano().hacerAgregarAListaPaso(j);
    }

    @Override
    public void crearApuesta(Mano aThis) throws ManoException {
        throw new ManoException("La apuesta ya esta iniciada!!!");
    }
    
    
    
}
