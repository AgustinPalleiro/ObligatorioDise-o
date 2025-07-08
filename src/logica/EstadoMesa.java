/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica;

import excepciones.MesaException;

/**
 *
 * @author Agus
 */
public abstract class EstadoMesa {
    
    private Mesa mesa;
    private String descripcion;
    private Mesa.Estados estado;
    
    public EstadoMesa(Mesa mesa,String descripcion,Mesa.Estados estMesa) {
        this.mesa = mesa;
        this.descripcion = descripcion; 
        estado = estMesa;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Mesa.Estados getEstado() {
        return estado;
    }
    
    public abstract void abrir() throws MesaException;
    public abstract void iniciar() throws MesaException;
    public abstract void finalizar() throws MesaException;
    
    //MÉTODOS DE MESA SEGÚN CORRESPONDA
    
    public abstract void agregarJugadorAMesa(Jugador jugador) throws MesaException;
    public abstract void quitarJugador(Jugador jugador) throws MesaException;
    public abstract void crearMano(Mesa mesa) throws MesaException;

}
