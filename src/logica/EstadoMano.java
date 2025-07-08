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
public abstract class EstadoMano {
    
    private Mano mano;
    private String descripcion;
    private Mano.Estados estado;
    
    public EstadoMano(Mano mano, String descripcion, Mano.Estados estMano) {
        this.mano = mano;
        this.descripcion = descripcion; 
        estado = estMano;
    }

    public Mano getMano() {
        return mano;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Mano.Estados getEstado() {
        return estado;
    }
    
    public abstract void esperandoApuesta() throws ManoException;
    public abstract void apuestaIniciada() throws ManoException;
    public abstract void pidiendoCartas() throws ManoException;
    public abstract void terminar() throws ManoException;
    
    
    //METODOS ESPECIFICOS
    
    public abstract void iniciarApuesta() throws ManoException;
    
    public abstract void pedirCartasParaJugador() throws ManoException;

    public abstract void agregaarAlistaPaso(Jugador j) throws ManoException;

    public abstract void crearApuesta(Mano aThis) throws ManoException;
        
         
    
}
