/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import excepciones.MesaException;
import java.util.ArrayList;
import logica.Administrador;
import logica.EstadoMano;
import logica.Fachada;
import observador.Observable;
import observador.Observador;
import logica.Eventos;
import logica.Jugador;
import logica.Mano;
import logica.Mesa;

/**
 *
 * @author Agus
 */
public class ControladorAdministrarMesa implements Observador{
    
    //Tareas de inicialización en el controlador
    
    private VistaAdministrarMesa vista;
    private Administrador admin;

    public ControladorAdministrarMesa(VistaAdministrarMesa vista, Administrador admin) {
        this.vista = vista;
        this.admin = admin;
        admin.agregarObservador(this); //Se registra para poder recibir eventos
        Fachada.getInstancia().agregarObservador(this);
        inicializarVista();
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
        if(evento.equals(Eventos.eventos.cambioListaMesas)){
            cargarListaMesas();
        }
    }
    
    public void crearMesa(int cantJugadores, double apuestaMinima, int comision) throws Exception{
        try {
            Fachada.getInstancia().crearMesa(cantJugadores, apuestaMinima, comision);
            vista.prepararProximoIngreso();
           vista.mostrarSuccess();
        } catch (MesaException ex) { //Cambiar error para excepción de Mesa
            vista.mostrarError(ex.getMessage());
        }
    }
    
    private void inicializarVista() {
        cargarListaMesas();
    }
    
    private void cargarListaMesas() {
        vista.mostrarMesas(admin.getNombreCompleto(), Fachada.getInstancia().recaudacionTotal(), Fachada.getInstancia().getListaMesas());
    }
    
    
    public void detallesMesa(int pos){
        if(pos >= 0){
            Mesa mesa = Fachada.getInstancia().getListaMesas().get(pos);
            ArrayList<Mano> manos = mesa.getListaManos();
            vista.mostrarDetallesManoDeMesa(manos);
        }
    }
}
