/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import logica.Mesa;

/**
 *
 * @author Agus
 */
public interface VistaIngresarAUnaMesa {

    public void mostrarMesas(String nombreCompleto, double saldoJugador, ArrayList<Mesa> listaMesasAbiertas);
    
    public void mostrarError(String message);
    
    public abstract void proximoCasoUso(Object resultado, Object resultado2);

    public void mesaSeleccionada(Mesa seleccionada);
    
}
