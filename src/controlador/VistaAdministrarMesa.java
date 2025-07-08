/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import logica.Mano;
import logica.Mesa;

/**
 *
 * @author Agus
 */
public interface VistaAdministrarMesa {
    
    public void mostrarMesas(String nombreCompleto, double montoTotal, ArrayList<Mesa> mesas);
    
    public void mostrarError(String message);
    
    public void prepararProximoIngreso();

    public void mostrarSuccess();

    //public void borrarDetalles();
    
    //public void mostrarDetallesMesa(int numeroMano, int cantJugadores, double montoTotalApostado, String estado, String nombreGanador, String figuraGanadora);

    public void mostrarDetallesManoDeMesa(ArrayList<Mano> manos);

}
